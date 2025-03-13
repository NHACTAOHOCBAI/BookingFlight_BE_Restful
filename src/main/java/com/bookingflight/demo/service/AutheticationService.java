package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.AuthenticationRequest;
import com.bookingflight.demo.dto.request.IntrospectRequest;
import com.bookingflight.demo.dto.response.AuthenticationResponse;
import com.bookingflight.demo.dto.response.IntrospectResponse;
import com.bookingflight.demo.entity.User;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AutheticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    //doc signerkey tu app.yaml
    protected String SIGNER_KEY;

    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.INCORRECT_PASSWORD);
        }
        String token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(authenticated)
                .build();
    }

    public String generateToken(User user) {
        // tao token can header, payload,signature(header+payload+key)
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        // tao header chua thuat toan ky :HS512
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("phucdeptry")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", user.getRole())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        // tao payload
        JWSObject jwsObject = new JWSObject(header, payload);
        // jwt chưa duoc ky
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            // ky theo MACSigner
            return jwsObject.serialize();
            // chuyen doi token thanh dang chuoi
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();
        // lay token ra
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        // tao ra ket qua dung
        SignedJWT signedJWT = SignedJWT.parse(token);
        // chuyen doi token sang dang jwt
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        // lay thoi gian het ban cua jwt
        boolean verified=signedJWT.verify(verifier);
        // kiem tra jwt do voi dap an dung
        return IntrospectResponse.builder()
                .valid( expirationDate.after(new Date()) && verified)
                .build();
    }
}

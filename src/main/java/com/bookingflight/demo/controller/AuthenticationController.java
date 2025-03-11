package com.bookingflight.demo.controller;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.AuthenticationRequest;
import com.bookingflight.demo.dto.request.IntrospectRequest;
import com.bookingflight.demo.dto.response.AuthenticationResponse;
import com.bookingflight.demo.dto.response.IntrospectResponse;
import com.bookingflight.demo.service.AutheticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AutheticationService autheticationService;

    @PostMapping("/token")
    APIResponse<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) {
        return APIResponse.<AuthenticationResponse>builder()
                .result(autheticationService.login(request))
                .build();
    }

    @PostMapping("/introspect")
    APIResponse<IntrospectResponse> introspect (@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return APIResponse.<IntrospectResponse>builder()
                .result(autheticationService.introspect(request))
                .build();
    }
}

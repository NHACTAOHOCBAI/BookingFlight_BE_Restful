package com.bookingflight.demo.configuration;

import lombok.NonNull;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
// spring boot 2.6 tro xuong de cau hinh
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {"/users","/auth/token","/auth/introspect"};
    @Value("${jwt.signerKey}")
    private String signerKey;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(requests ->
                requests.anyRequest().permitAll());
        httpSecurity.csrf(AbstractHttpConfigurer::disable) ;
        // CRSF: CSRF là một kỹ thuật tấn công trong đó kẻ tấn công lợi dụng một session hợp lệ của người dùng
        return httpSecurity.build();
    }
    // KHONG XOA CODE NAY, TAM THOI TAT SECURITY
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity.authorizeHttpRequests(requests ->
//                    requests.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS).permitAll()
//                    .anyRequest().authenticated());
//            // chi ra quyen truy cap cua cac http request
//            httpSecurity.oauth2ResourceServer(oauth2->
//                    oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
//            // chuc nang: dung de xac thuc jwt
//            // custom su dung jwt de xac thuc, chi dinh customized decoder
//            httpSecurity.csrf(AbstractHttpConfigurer::disable) ;
//            // CRSF: CSRF là một kỹ thuật tấn công trong đó kẻ tấn công lợi dụng một session hợp lệ của người dùng
//        return httpSecurity.build();
//    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec( signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}

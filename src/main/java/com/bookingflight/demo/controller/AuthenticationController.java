package com.bookingflight.demo.controller;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.AuthenticationRequest;
import com.bookingflight.demo.dto.response.AuthenticationResponse;
import com.bookingflight.demo.service.AutheticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AutheticationService autheticationService;

    @PostMapping("/login")
    APIResponse<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) {
        boolean result=autheticationService.login(request);
        return APIResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(result)
                        .build())
                .build();
    }
}

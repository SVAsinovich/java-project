package com.example.java_project.controller;

import com.example.java_project.domain.dto.JwtAuthenticationResponse;
import com.example.java_project.domain.dto.SignInRequest;
import com.example.java_project.domain.dto.SignUpRequest;
import com.example.java_project.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Sing up")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Sign in")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}

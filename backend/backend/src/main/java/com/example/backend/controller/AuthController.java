package com.example.backend.controller;


import com.example.backend.dto.*;
import com.example.backend.entity.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRegisterRequestDto req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auth.register(req));
    }

    @PostMapping("/login")
    public AuthResponseDto login(@Valid @RequestBody LoginRequestDto req) {
        return auth.login(req);
    }

    @GetMapping("/me")
    public UserResponseDto me(@AuthenticationPrincipal User user) {
        return AuthService.toResponse(user);
    }
}
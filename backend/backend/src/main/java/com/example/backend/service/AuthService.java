package com.example.backend.service;

import com.example.backend.dto.AuthResponseDto;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.security.JwtUtil;

import com.example.backend.dto.UserRegisterRequestDto;
import com.example.backend.dto.UserResponseDto;
import com.example.backend.entity.User;
import com.example.backend.exception.ApiException;
import com.example.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public AuthService(UserRepository users, PasswordEncoder encoder, JwtUtil jwt) {
        this.users = users;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public UserResponseDto register(UserRegisterRequestDto req) {
        if (users.existsByEmail(req.getEmail()))
            throw new ApiException(HttpStatus.CONFLICT, "Email already in use");
        if (users.existsByUsername(req.getUsername()))
            throw new ApiException(HttpStatus.CONFLICT, "Username already taken");

        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        users.save(u);
        return toResponse(u);
    }

    public AuthResponseDto login(LoginRequestDto req) {
        User u = users.findByEmail(req.getEmail())
            .filter(x -> encoder.matches(req.getPassword(), x.getPassword()))
            .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
        return new AuthResponseDto(jwt.generateToken(u.getEmail()), "Bearer");
    }

    public static UserResponseDto toResponse(User u) {
        return new UserResponseDto(u.getId(), u.getUsername(), u.getEmail(), u.getCreatedAt());
    }
}
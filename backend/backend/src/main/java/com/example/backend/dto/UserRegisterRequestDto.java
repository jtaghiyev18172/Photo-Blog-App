package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRegisterRequestDto {
    @NotBlank(message = "Username is required")
    private String username;
    @Email(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return username;}
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}
}

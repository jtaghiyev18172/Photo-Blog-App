package com.example.backend.dto;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(int status, String message, Map<String, String> fieldErrors, Instant timestamp) {
    
}
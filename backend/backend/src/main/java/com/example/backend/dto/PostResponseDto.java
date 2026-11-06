package com.example.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDto {
    private Long id;
    private String title;
    private UserResponseDto author;
    private List<PostImageResponseDto> images;
    private LocalDateTime createdAt;

    public PostResponseDto(Long id, String title, UserResponseDto author, List<PostImageResponseDto> images, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.images = images;
        this.createdAt = createdAt;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public UserResponseDto getAuthor() { return author; }
    public List<PostImageResponseDto> getImages() { return images; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
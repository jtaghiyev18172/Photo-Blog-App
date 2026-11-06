package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PostCreateRequestDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    private List<PostImageRequestDto> images;

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public List<PostImageRequestDto> getImages() { return images; }
    public void setImages(List<PostImageRequestDto> images) { this.images = images; }
}
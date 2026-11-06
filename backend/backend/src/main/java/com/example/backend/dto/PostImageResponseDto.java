package com.example.backend.dto;

public class PostImageResponseDto {
    private Long id;
    private String imageUrl;
    private int displayOrder;

    public PostImageResponseDto(Long id, String imageUrl, int displayOrder) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.displayOrder = displayOrder;
    }

    // Getters
    public Long getId() { return id; }
    public String getImageUrl() { return imageUrl; }
    public int getDisplayOrder() { return displayOrder; }
}
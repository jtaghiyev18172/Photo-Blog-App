package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    public Post post;


    @Column(name = "display_order", nullable = false)
    public int displayOrder;

    @Column(nullable = false)
    public String imageUrl;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
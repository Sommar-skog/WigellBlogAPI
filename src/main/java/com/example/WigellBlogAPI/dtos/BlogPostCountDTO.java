package com.example.WigellBlogAPI.dtos;

import java.time.LocalDateTime;

public class BlogPostCountDTO {

    private LocalDateTime timeStamp;
    private Long BlogPostCountInSystem;

    public BlogPostCountDTO() {
        this.timeStamp = LocalDateTime.now();
    }

    public BlogPostCountDTO(Long BlogPostCountInSystem) {
        this.BlogPostCountInSystem = BlogPostCountInSystem;
        this.timeStamp = LocalDateTime.now();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getBlogPostCountInSystem() {
        return BlogPostCountInSystem;
    }

    public void setBlogPostCountInSystem(Long blogPostCountInSystem) {
        BlogPostCountInSystem = blogPostCountInSystem;
    }

    @Override
    public String toString() {
        return "BlogPostCountDTO{" +
                "timeStamp=" + timeStamp +
                ", BlogPostCountInSystem=" + BlogPostCountInSystem +
                '}';
    }
}

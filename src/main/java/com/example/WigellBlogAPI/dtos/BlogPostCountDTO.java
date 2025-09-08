package com.example.WigellBlogAPI.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * DTO for returning the total number of blog posts in the system.
 * Includes a timestamp of when the count was retrieved.
 */

public class BlogPostCountDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Stockholm")
    private LocalDateTime timeStamp;
    private Long blogPostCountInSystem;

    public BlogPostCountDTO() {
        this.timeStamp = LocalDateTime.now();
    }

    public BlogPostCountDTO(Long blogPostCountInSystem) {
        this.blogPostCountInSystem = blogPostCountInSystem;
        this.timeStamp = LocalDateTime.now();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getBlogPostCountInSystem() {
        return blogPostCountInSystem;
    }

    public void setBlogPostCountInSystem(Long newBlogPostCountInSystem) {
        blogPostCountInSystem = newBlogPostCountInSystem;
    }

    @Override
    public String toString() {
        return "BlogPostCountDTO{" +
                "timeStamp=" + timeStamp +
                ", BlogPostCountInSystem=" + blogPostCountInSystem +
                '}';
    }
}

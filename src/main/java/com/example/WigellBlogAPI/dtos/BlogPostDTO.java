package com.example.WigellBlogAPI.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class BlogPostDTO {

    private Long id;
    private String title;
    private String content;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Stockholm")
    private LocalDateTime postedTime;

    public BlogPostDTO() {

    }

    public BlogPostDTO(Long id, String title, String content, String userId, LocalDateTime postedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.postedTime = postedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(LocalDateTime postedTime) {
        this.postedTime = postedTime;
    }

    @Override
    public String toString() {
        return "BlogPostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", postedTime=" + postedTime +
                '}';
    }
}

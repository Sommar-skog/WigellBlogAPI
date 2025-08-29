package com.example.WigellBlogAPI.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "CLOB", nullable = false) //"CLOB" - Texten kan bli hur lång som helst/ istället för length
    private String content;

    @Column(nullable = false, length = 36) //sub alltid 36 tecken långt i keycloak
    private String userId;

    @Column(nullable = false)
    private LocalDateTime postedTime;

    public BlogPost() {
        postedTime = LocalDateTime.now();
    }

    public BlogPost(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        postedTime = LocalDateTime.now();
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
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", postedTime=" + postedTime +
                '}';
    }
}

package com.example.WigellBlogAPI.dtos;

/**
 * DTO for updating an existing blog post.
 * Contains only fields that are allowed to be updated — title, content, and ID (So that the blogPost can be found).
 * User ID and username are excluded and cannot be modified through this DTO.
 */

public class UpdateBlogPostDTO {

    private Long id;
    private String title;
    private String content;

    public UpdateBlogPostDTO() {

    }

    public UpdateBlogPostDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    @Override
    public String toString() {
        return "UpdateBlogPostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

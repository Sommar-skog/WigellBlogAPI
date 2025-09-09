package com.example.WigellBlogAPI.dtos;



 //DTO for creating a new blog post. Contains only fields that the user is allowed to provide during creation.


public class CreateBlogPostDTO {

    private String title;
    private String content;

    public CreateBlogPostDTO() {

    }

    public CreateBlogPostDTO(String title, String content) {
        this.title = title;
        this.content = content;
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
        return "CreateBlogPostDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.dtos.BlogPostCountDTO;
import com.example.WigellBlogAPI.entities.BlogPost;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface BlogPostService {

    List<BlogPost> getAllBlogPosts();
    BlogPost getBlogPostById(Long id);
    BlogPost createBlogPost(BlogPost blogPost, Jwt jwt);
    BlogPost updateBlogPost(BlogPost blogPost, Jwt jwt);
    String deleteBlogPost(Long blogPostId, Jwt jwt);
    BlogPostCountDTO countBlogPostsInSystem();
}

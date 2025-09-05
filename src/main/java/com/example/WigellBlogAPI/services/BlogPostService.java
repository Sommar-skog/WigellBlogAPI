package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.dtos.BlogPostCountDTO;
import com.example.WigellBlogAPI.dtos.BlogPostDTO;
import com.example.WigellBlogAPI.entities.BlogPost;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface BlogPostService {

    List<BlogPostDTO> getAllBlogPosts();
    BlogPostDTO getBlogPostById(Long id);
    BlogPostDTO createBlogPost(BlogPost blogPost, Jwt jwt);
    BlogPostDTO updateBlogPost(BlogPost blogPost, Jwt jwt);
    String deleteBlogPost(Long blogPostId, Jwt jwt);
    BlogPostCountDTO countBlogPostsInSystem();
}

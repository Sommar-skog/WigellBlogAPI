package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.dtos.BlogPostCountDTO;
import com.example.WigellBlogAPI.dtos.BlogPostDTO;
import com.example.WigellBlogAPI.dtos.CreateBlogPostDTO;
import com.example.WigellBlogAPI.dtos.UpdateBlogPostDTO;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface BlogPostService {

    List<BlogPostDTO> getAllBlogPosts();
    BlogPostDTO getBlogPostById(Long id);
    BlogPostDTO createBlogPost(CreateBlogPostDTO blogPost, Jwt jwt);
    BlogPostDTO updateBlogPost(UpdateBlogPostDTO blogPost, Authentication auth);
    String deleteBlogPost(Long blogPostId, Authentication auth);
    BlogPostCountDTO countBlogPostsInSystem();
}

package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.entities.BlogPost;

import java.util.List;

public interface BlogPostService {

    List<BlogPost> getAllBlogPosts();
    BlogPost getBlogPostById(Long id);
    BlogPost createBlogPost(BlogPost blogPost, String sub);
    BlogPost updateBlogPost(Long blogpostId, BlogPost blogPost, String sub);
    String deleteBlogPost(Long blogPostId, String sub);

    //DAO?
    String countBlogPostsInSystem();
}

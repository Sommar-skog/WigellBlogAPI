package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.entities.BlogPost;
import com.example.WigellBlogAPI.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost getBlogPostById(Long id) {
        return blogPostRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post with id " + id + " not found"));
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost, String sub) {
        validateBlogPost(blogPost);
        blogPost.setUserId(sub);
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(Long blogpostId, BlogPost blogPost, String sub) {
        return null;
    }

    @Override
    public String deleteBlogPost(Long blogPostId, String sub) {
        return "";
    }

    @Override
    public String countBlogPostsInSystem() {
        return "";
    }

    private void validateBlogPost(BlogPost blogPost) {
        if (blogPost.getTitle() == null || blogPost.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title cannot be null or blank");
        }
        if (blogPost.getContent() == null || blogPost.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content cannot be null or blank");
        }
    }

    private void validateBlogPostForUpdate(BlogPost blogPost, String sub) {
        if (!Objects.equals(blogPost.getUserId(), sub))
        {

        }

    }
}

package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.entities.BlogPost;
import com.example.WigellBlogAPI.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;

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
        validateCreateBlogPost(blogPost);
        blogPost.setUserId(sub);
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(BlogPost blogPost, Jwt jwt) {
        validateBlogPostExist(blogPost.getId());
        validateOwnerOrAdmin(blogPost.getUserId(),jwt);

        if (blogPost.getTitle() != null && !blogPost.getTitle().isBlank()){
            blogPost.setTitle(blogPost.getTitle());
        }
        if (blogPost.getContent() != null && !blogPost.getContent().isBlank()){
            blogPost.setContent(blogPost.getContent());
        }
        return blogPostRepository.save(blogPost);
    }

    @Override
    public String deleteBlogPost(Long blogPostId, Jwt jwt) {
        BlogPost postToDelete = validateBlogPostExist(blogPostId);
        validateOwnerOrAdmin(postToDelete.getUserId(),jwt);
        blogPostRepository.delete(postToDelete);
        return "Blog post with id " + blogPostId + " deleted successfully";
    }

    @Override
    public String countBlogPostsInSystem() {
        return "";
    }

    private void validateCreateBlogPost(BlogPost blogPost) {
        if (blogPost.getTitle() == null || blogPost.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title cannot be null or blank");
        }
        if (blogPost.getContent() == null || blogPost.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content cannot be null or blank");
        }
    }

    private void validateOwnerOrAdmin(String blogPostUserId, Jwt jwt) {
        List<String> roles = jwt.getClaim("authorities");
        boolean isAdmin = roles.contains("ROLE_wigellblog-admin");
        String sub = jwt.getClaim("sub");

        if (!isAdmin) {
            if (!Objects.equals(blogPostUserId, sub)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not allowed to update blog post");
            }
        }
    }

    private BlogPost validateBlogPostExist(Long blogPostId) {
        return blogPostRepository.findById(blogPostId).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post with id " + blogPostId + " not found"));
    }
}

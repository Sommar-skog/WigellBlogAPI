package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.entities.BlogPost;
import com.example.WigellBlogAPI.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
    public BlogPost updateBlogPost(BlogPost blogPost, Jwt jwt) {
        validateBlogPostForUpdate(blogPost,jwt);
        if (blogPost.getTitle() != null && !blogPost.getTitle().isBlank()){
            blogPost.setTitle(blogPost.getTitle());
        }
        if (blogPost.getContent() != null && !blogPost.getContent().isBlank()){
            blogPost.setContent(blogPost.getContent());
        }
        return blogPostRepository.save(blogPost);
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

    private void validateBlogPostForUpdate(BlogPost blogPost, Jwt jwt) {

        List<String> roles = jwt.getClaim("authorities");
        boolean isAdmin = roles.contains("ROLE_WigellBlog_Admin");
        String sub = jwt.getClaim("sub");

        if (!isAdmin) {
            if (!Objects.equals(blogPost.getUserId(), sub)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not allowed to update blog post");
            }
        }
        if (blogPostRepository.findById(blogPost.getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post with id " + blogPost.getId() + " not found");
        }
    }
}

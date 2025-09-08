package com.example.WigellBlogAPI.controllers;

import com.example.WigellBlogAPI.dtos.BlogPostCountDTO;
import com.example.WigellBlogAPI.dtos.BlogPostDTO;
import com.example.WigellBlogAPI.dtos.CreateBlogPostDTO;
import com.example.WigellBlogAPI.dtos.UpdateBlogPostDTO;
import com.example.WigellBlogAPI.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<BlogPostDTO>> getAllPosts() {
        return ResponseEntity.ok(blogPostService.getAllBlogPosts());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BlogPostDTO> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.getBlogPostById(id));
    }

    @PreAuthorize("hasRole('wigellblog-user')")
    @PostMapping("/newpost")
    public ResponseEntity<BlogPostDTO> createPost(@RequestBody CreateBlogPostDTO post, @AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(blogPostService.createBlogPost(post, jwt), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('wigellblog-user','wigellblog-admin')")
    @PutMapping("/updatepost")
    public ResponseEntity<BlogPostDTO> updatePost(@RequestBody UpdateBlogPostDTO post, Authentication auth) {
        return ResponseEntity.ok(blogPostService.updateBlogPost(post,auth)) ;
    }

    @PreAuthorize("hasAnyRole('wigellblog-user','wigellblog-admin')")
    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(blogPostService.deleteBlogPost(id,auth)) ;
    }

    @PreAuthorize("hasRole('wigellblog-admin')")
    @GetMapping("/count")
    public ResponseEntity<BlogPostCountDTO> getCount() {
        return ResponseEntity.ok(blogPostService.countBlogPostsInSystem());
    }
}

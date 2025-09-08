package com.example.WigellBlogAPI.controllers;

import com.example.WigellBlogAPI.dtos.BlogPostCountDTO;
import com.example.WigellBlogAPI.dtos.BlogPostDTO;
import com.example.WigellBlogAPI.dtos.CreateBlogPostDTO;
import com.example.WigellBlogAPI.dtos.UpdateBlogPostDTO;
import com.example.WigellBlogAPI.entities.BlogPost;
import com.example.WigellBlogAPI.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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


    //          -- AUTENTISERADE ANVÄNDARE --
    @GetMapping("/posts")
    public ResponseEntity<List<BlogPostDTO>> getAllPosts() {
        return ResponseEntity.ok(blogPostService.getAllBlogPosts());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BlogPostDTO> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.getBlogPostById(id));
    }

    //          -- AUKTORISERADE ANVÄNDARE - WigellBlog-User --
    @PreAuthorize("hasRole('wigellblog-user')")
    @PostMapping("/newpost")
    public ResponseEntity<BlogPostDTO> createPost(@RequestBody CreateBlogPostDTO post, @AuthenticationPrincipal Jwt jwt) {
        System.out.println("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println("User: " + SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity<>(blogPostService.createBlogPost(post, jwt), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('wigellblog-user','wigellblog-admin')")
    @PutMapping("/updatepost")
    public ResponseEntity<BlogPostDTO> updatePost(@RequestBody UpdateBlogPostDTO post, Authentication auth) {
        System.out.println("Authorities: " + auth.getAuthorities());
        return ResponseEntity.ok(blogPostService.updateBlogPost(post,auth)) ;
    }

    //          -- AUKTORISERADE ANVÄNDARE - WigellBlog-User/ WigellBlog-Admin --
    @PreAuthorize("hasAnyRole('wigellblog-user','wigellblog-admin')")
    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Authentication auth) {
        System.out.println("Authorities: " + auth.getAuthorities());
        return ResponseEntity.ok(blogPostService.deleteBlogPost(id,auth)) ;
    }

    //          -- AUKTORISERADE ANVÄNDARE -  WigellBlog-Admin --
    @PreAuthorize("hasRole('wigellblog-admin')")
    @GetMapping("/count")
    public ResponseEntity<BlogPostCountDTO> getCount() {
        System.out.println("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return ResponseEntity.ok(blogPostService.countBlogPostsInSystem());
    }
}

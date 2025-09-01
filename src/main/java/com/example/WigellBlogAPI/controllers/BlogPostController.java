package com.example.WigellBlogAPI.controllers;

import com.example.WigellBlogAPI.dtos.BlogPostCountDTO;
import com.example.WigellBlogAPI.entities.BlogPost;
import com.example.WigellBlogAPI.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        return ResponseEntity.ok(blogPostService.getAllBlogPosts());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.getBlogPostById(id));
    }

    //          -- AUKTORISERADE ANVÄNDARE - WigellBlog-User --
    @PostMapping("/newpost")
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost post, Jwt jwt) {
        return new ResponseEntity<>(blogPostService.createBlogPost(post, jwt), HttpStatus.CREATED);
    }

    @PutMapping("/updatepost")
    public ResponseEntity<BlogPost> updatePost(@RequestBody BlogPost post, Jwt jwt) {
        return ResponseEntity.ok(blogPostService.updateBlogPost(post,jwt)) ;
    }

    //          -- AUKTORISERADE ANVÄNDARE - WigellBlog-User/ WigellBlog-Admin --
    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Jwt jwt) {
        return ResponseEntity.ok(blogPostService.deleteBlogPost(id,jwt)) ;
    }

    //          -- AUKTORISERADE ANVÄNDARE -  WigellBlog-Admin --
    @GetMapping("/count")
    public ResponseEntity<BlogPostCountDTO> getCount() {
        return ResponseEntity.ok(blogPostService.countBlogPostsInSystem());
    }
}

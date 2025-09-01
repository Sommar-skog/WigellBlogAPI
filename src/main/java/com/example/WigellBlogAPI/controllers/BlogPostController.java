package com.example.WigellBlogAPI.controllers;

import com.example.WigellBlogAPI.entities.BlogPost;
import com.example.WigellBlogAPI.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        return null;
    }

    //          -- AUKTORISERADE ANVÄNDARE - WigellBlog-User --
    @PostMapping("/newpost") //TODO Få med SUB in här
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost post) {
        return null;
    }

    @PutMapping("/updatepost") //TODO Få in SUB så att det blir rätt person som uppdaterar
    public ResponseEntity<BlogPost> updatePost(@RequestBody BlogPost post, Jwt jwt) {
        return blogPostService.updateBlogPost(post,jwt);
    }

    //          -- AUKTORISERADE ANVÄNDARE - WigellBlog-User/ WigellBlog-Admin --
    @DeleteMapping("/deletepost/{id}") //TODO få in sub. Kontrollera rätt användare eller Admin.
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        return null;
    }

    //          -- AUKTORISERADE ANVÄNDARE -  WigellBlog-Admin --
    @GetMapping("/count") //TODO ska jag skicka en DAO med Json-format på statistik här?
    public ResponseEntity<Integer> getCount() {
        return null;
    }
}

package com.example.WigellBlogAPI.services;

import com.example.WigellBlogAPI.dtos.BlogPostCountDTO;
import com.example.WigellBlogAPI.dtos.BlogPostDTO;
import com.example.WigellBlogAPI.entities.BlogPost;
import com.example.WigellBlogAPI.repositories.BlogPostRepository;
import com.example.WigellBlogAPI.utils.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;


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
    public List<BlogPostDTO> getAllBlogPosts() {
        return blogPostRepository.findAll()
                .stream()
                .map(blogPost -> new BlogPostDTO(
                        blogPost.getId(),
                        blogPost.getTitle(),
                        blogPost.getContent(),
                        blogPost.getUserId(),
                        blogPost.getUsername(),
                        blogPost.getPostedTime()
                )).toList();
    }

    @Override
    public BlogPostDTO getBlogPostById(Long id) {
       BlogPost blogPost = blogPostRepository.findById(id)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post with id " + id + " not found"));

       return new BlogPostDTO(blogPost.getId(), blogPost.getTitle(), blogPost.getContent(), blogPost.getUserId(),blogPost.getUsername(),blogPost.getPostedTime());
    }

    @Override
    public BlogPostDTO createBlogPost(BlogPost blogPost, Jwt jwt) {
        validateCreateBlogPost(blogPost);
        blogPost.setUserId(UserInfoUtil.getUserIdFromJwt(jwt));
        blogPost.setUsername(UserInfoUtil.getUsernameFromJwt(jwt));
        BlogPost savedBlogPost =  blogPostRepository.save(blogPost);

        System.out.println(blogPost);

        return new BlogPostDTO(savedBlogPost.getId(), savedBlogPost.getTitle(), savedBlogPost.getContent(), savedBlogPost.getUserId(),savedBlogPost.getUsername(),savedBlogPost.getPostedTime());
    }

    @Override
    public BlogPostDTO updateBlogPost(BlogPost blogPost, Authentication auth) {
        BlogPost blogPostToUpdate = validateBlogPostExist(blogPost.getId());
        validateOwnerOrAdmin(blogPostToUpdate.getUserId(),auth);

        if (blogPost.getTitle() != null && !blogPost.getTitle().isBlank()){
            blogPostToUpdate.setTitle(blogPost.getTitle());
        }
        if (blogPost.getContent() != null && !blogPost.getContent().isBlank()){
            blogPostToUpdate.setContent(blogPost.getContent());
        }

        BlogPost updatedBlogPost = blogPostRepository.save(blogPostToUpdate);
        return new BlogPostDTO(updatedBlogPost.getId(), updatedBlogPost.getTitle(), updatedBlogPost.getContent(), updatedBlogPost.getUserId(), updatedBlogPost.getUserId(), updatedBlogPost.getPostedTime());
    }

    @Override
    public String deleteBlogPost(Long blogPostId, Authentication auth) {
        BlogPost postToDelete = validateBlogPostExist(blogPostId);
        validateOwnerOrAdmin(postToDelete.getUserId(),auth);
        blogPostRepository.delete(postToDelete);
        return "Blog post with id " + blogPostId + " deleted successfully";
    }

    @Override
    public BlogPostCountDTO countBlogPostsInSystem() {
        Long count = blogPostRepository.count();
        return new BlogPostCountDTO(count);
    }

    private void validateCreateBlogPost(BlogPost blogPost) {
        if (blogPost.getTitle() == null || blogPost.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title cannot be null or blank");
        }
        if (blogPost.getContent() == null || blogPost.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content cannot be null or blank");
        }
    }

    private void validateOwnerOrAdmin(String blogPostUserId, Authentication auth) {
        /*List<String> roles = jwt.getClaim("authorities");
        boolean isAdmin = roles.contains("ROLE_wigellblog-admin");
        String sub = jwt.getClaim("sub");
*/
        boolean isAdmin = UserInfoUtil.hasRoleFromAuthentication(auth,"wigellblog-admin");
        String sub = UserInfoUtil.getUserIdFromAuthentication(auth);
        System.out.println("blogPostUserId: " + blogPostUserId);
        System.out.println("isAdmin: " + isAdmin);
        System.out.println("authenticated-sub: " + sub);

        if (!isAdmin) {
            if (!Objects.equals(blogPostUserId, sub)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not owner of blog post or admin");
            }
        }
    }

    private BlogPost validateBlogPostExist(Long blogPostId) {
        return blogPostRepository.findById(blogPostId).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post with id " + blogPostId + " not found"));
    }
}

package com.myBlogNest.controller;

import com.myBlogNest.payload.PostDto;
import com.myBlogNest.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {


    private PostService postService;

    // Constructor based dependency injection for PostService
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create a new blog post via a POST request
    // Example URL: http://localhost:8080/api/posts/create
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }



}

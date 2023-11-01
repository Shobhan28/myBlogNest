package com.myBlogNest.service;

import com.myBlogNest.payload.PostDto;
import com.myBlogNest.payload.PostResponse;

public interface PostService {
    // Create a new blog post
    PostDto createPost(PostDto postDto);

    // Delete a blog post by its user ID
    void deletePostById(long userId);

    // Update a blog post by its user ID with new data
    PostDto updatePost(long userId,PostDto postDto);

    // Find a blog post by its post ID
    PostDto findPostById(long postId);

    // Retrieve a list of all blog posts
    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}


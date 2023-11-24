package com.myBlogNest.controller;

import com.myBlogNest.payload.CommentDto;
import com.myBlogNest.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    // This method handles HTTP POST requests to save a comment for a specific post.
    // The {postId} in the URL is a path variable that specifies the ID of the post to which the comment belongs.

    // http://localhost:8080/api/comments/{postId}
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto, @PathVariable long postId) {

        // Call the CommentService to save the comment and associate it with the specified post.
        CommentDto commentDto1 = commentService.saveComment(commentDto, postId);

        // Return a ResponseEntity with the saved comment and an HTTP status code of OK (200).
        return new ResponseEntity<>(commentDto1, HttpStatus.OK);
    }


    // http://localhost:8080/api/comments/{commentId}
    @DeleteMapping("{commentId}")
    public ResponseEntity<String> deleteById(@PathVariable long commentId) {

        commentService.deleteByCommentId(commentId);

        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }


    // http://localhost:8080/api/comments/{commentId}
    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateCommentId(@PathVariable long id, @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.updateCommentById(commentDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    // http://localhost:8080/api/comments
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> listComment = commentService.getAllComment();

        return new ResponseEntity<>(listComment, HttpStatus.OK);
    }
}
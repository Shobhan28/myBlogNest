package com.myBlogNest.service.impl;

import com.myBlogNest.entity.Comment;
import com.myBlogNest.entity.Post;
import com.myBlogNest.exception.ResourceNotFoundException;
import com.myBlogNest.payload.CommentDto;
import com.myBlogNest.repository.CommentRepository;
import com.myBlogNest.repository.PostRepository;
import com.myBlogNest.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepo;

    private ModelMapper mapper;

    private PostRepository postRepo;

    public CommentServiceImpl(CommentRepository commentRepo,PostRepository postRepo,ModelMapper mapper) {
        this.commentRepo = commentRepo;
        this.postRepo=postRepo;
        this.mapper=mapper;
    }

    @Override
    public CommentDto saveComment(CommentDto dto, long postId) {
        // Find the post by its ID or throw a ResourceNotFoundException if not found
        Post post= postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not found with Postid: "+postId));
        // Create a new Comment entity and set its properties from the CommentDto
        Comment comment = mapToEntity(dto);

//        Comment comment=new Comment();
//        comment.setId(dto.getId());
//        comment.setName(dto.getName());
//        comment.setEmail(dto.getEmail());
//        comment.setBody(dto.getBody());
        comment.setPost(post);
        // Save the comment entity to the database
        commentRepo.save(comment);
        // Map the saved comment entity back to a CommentDto and return it
        return mapToDto(comment);
    }

    @Override
    public void deleteByCommentId(long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("not found with this comment id: " + commentId));
        commentRepo.deleteById(commentId);
    }

    @Override
    public CommentDto updateCommentById(CommentDto commentDto, long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not exist with id: " + id));
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment saveComment = commentRepo.save(comment);

        return mapToDto(saveComment);
    }

    @Override
    public List<CommentDto> getAllComment() {

        List<Comment> all = commentRepo.findAll();

        List<CommentDto> commentDtos=new ArrayList<>();

        for (Comment comment : all) {
            // Convert each Comment to CommentDto and add it to the list
            CommentDto commentDto = mapToDto(comment);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }



    // Helper method to map a Comment entity to a CommentDto
    public CommentDto mapToDto(Comment comment){
        CommentDto commentDto=mapper.map(comment,CommentDto.class);

//        CommentDto dto=new CommentDto();
//        dto.setId(comment.getId());
//        dto.setName(comment.getName());
//        dto.setEmail(comment.getEmail());
//        dto.setBody(comment.getBody());
        return commentDto;
    }

    public Comment mapToEntity(CommentDto commentDto){
        Comment comment=mapper.map(commentDto,Comment.class);
        return comment;
    }
}
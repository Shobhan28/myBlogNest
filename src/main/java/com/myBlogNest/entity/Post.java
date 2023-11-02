package com.myBlogNest.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table (name = "posts_table" ,  uniqueConstraints = {@UniqueConstraint(columnNames ={"title"})})
public class Post {

    // The unique identifier for each post
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // The title of the blog post, which is required and mapped to the "title" column
    @Column(name="title",nullable=false)
    private String title;

    // The description of the blog post, which is required and mapped to the "description" column
    @Column(name="description", nullable=false)
    private String description;

    // The content of the blog post, which is required and mapped to the "content" column
    @Column(name="content", nullable=false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments=new ArrayList<>();
}

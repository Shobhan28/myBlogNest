package com.myBlogNest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    //not mandatory to write @Manytomany, because if the third table is introduced,
    //then automatically it becomes manytomany
    @ManyToMany(mappedBy = "roles")
    private Set<User> userSet = new HashSet<>();
}

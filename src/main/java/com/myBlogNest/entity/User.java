package com.myBlogNest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table (name="user", uniqueConstraints = {
        @UniqueConstraint(columnNames={"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    private String username;

    private String email;
    private String password;


}

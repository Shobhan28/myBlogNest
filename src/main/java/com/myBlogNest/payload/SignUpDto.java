package com.myBlogNest.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDto {

    private String name;
    private String Username;
    private String email;
    private String password;
}

package com.myBlogNest.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {

    private String UsernameOrEmail;
    private String password;
}

package com.myBlogNest.controller;


import com.myBlogNest.entity.Role;
import com.myBlogNest.entity.User;
import com.myBlogNest.payload.JWTAuthResponse;
import com.myBlogNest.payload.LoginDto;
import com.myBlogNest.payload.SignUpDto;
import com.myBlogNest.repository.RoleRepository;
import com.myBlogNest.repository.UserRepository;
import com.myBlogNest.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;


    //   http://localhost:8080/api/auth/signup
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/signup")
    public ResponseEntity<?> CreateUser(@RequestBody SignUpDto signDto) {


        // Now before creating a user, check whether the username or email already exists,
        // if they exist, return a response with an error message, otherwise, create a user

        // add check for username exists in a DB
        if (userRepository.existsByUsername(signDto.getUsername())) {
            return new ResponseEntity<>("User Already Exist", HttpStatus.BAD_REQUEST);
        }
        // add check for email exists in a DB
        if (userRepository.existsByEmail(signDto.getEmail())) {
            return new ResponseEntity<>("Email Already Exist", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signDto.getName());
        user.setEmail(signDto.getEmail());
        user.setUsername((signDto.getUsername()));
        user.setPassword(passwordEncoder.encode(signDto.getPassword()));

//        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        Role roles = roleRepository.findByName("ROLE_USER").get();

        Set<Role> role = new HashSet<>();
        role.add(roles);
        user.setRoles(role);

        User saveUser = userRepository.save(user);

        SignUpDto signupDto1 = new SignUpDto();
        signupDto1.setName(saveUser.getName());
        signupDto1.setEmail(saveUser.getEmail());
        signupDto1.setUsername(saveUser.getUsername());

        return new ResponseEntity<>(signupDto1, HttpStatus.CREATED);
        // If you want to return a success message instead
       // return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    //  http://localhost:8080/api/auth/signin
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto
    loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                        loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token from TokenProvider class
        String getToken = tokenProvider.generateToken(authentication);

        // returning back that token to postman
        return ResponseEntity.ok(new JWTAuthResponse(getToken));
    }
}


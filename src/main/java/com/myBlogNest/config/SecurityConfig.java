package com.myBlogNest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity  // Annotation to enable Spring Security
@EnableGlobalMethodSecurity(prePostEnabled = true)  // Enable method-level security with prePost annotations
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserServiceDetails userDetailsService;

    @Bean  // Bean to provide an instance of the PasswordEncoder
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Configure security settings for HTTP requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/comments/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    // Bean to provide an instance of UserDetailsService
/*   @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user = User.builder().username("shubh").
                password(getEncoder().encode("pass")).roles("USER").
                build();

        UserDetails admin = User.builder().username("shobhan")
                .password(getEncoder()
                        .encode("passs")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(admin,user);
}   */
    //now we are not going to use In-memory Authentication here.


    //WHAT IS IN MEMORY AUTHENTICATION
    //WHEN IMAGINE 7-8 ROLES ARE THERE FIRST YOU WANT TO DO AUTHORIZATION WITHOUT DEALING WITH DATABASE
    //THEN YOU CREATE OBJECT LIKE THIS WITH THE ROLES AND DO YOUR TESTING WITHOUT DATABASE
    //LATTER ONE CAN STORE OUR USERNAME AND PASSWORD IN OUR DATABASE

    // In-memory authentication allows you to define users and roles without using a database.
    // This is useful for testing and prototyping. Later, you can switch to a database-backed
    // UserDetailsService when needed.

    @Override
    protected void configure (AuthenticationManagerBuilder authBuilder ) throws Exception{
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}

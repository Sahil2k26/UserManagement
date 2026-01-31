package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.dto.UserDto;
import com.usermanagement.usermanagement.entity.User;
import com.usermanagement.usermanagement.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;

    }
    @GetMapping("/hello")
     public  String sayHello(){
        return "hello world";
    }
    @GetMapping("/user")
    public UserDto getUser(){
        return new UserDto(1L,"sahil","");

    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }


//    @PostMapping("/api/otp")

}

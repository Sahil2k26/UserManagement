package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.dto.CreateUserDto;
import com.usermanagement.usermanagement.dto.UserDto;
import com.usermanagement.usermanagement.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor // to automatically initialize final data members as beans
public class UserController {
    private final UserService userService;

    @GetMapping
    public String welcome(){
        return "Welcome to UsersManger";
    }

    @GetMapping("/hello")
     public  String sayHello(){
        return "hello world";
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid  CreateUserDto req){
        System.out.println("hello"+req.getPassword()+" "+ req.getPhoneNo());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody @Valid CreateUserDto req){

        return ResponseEntity.ok(userService.updateUserById(id,req));
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserDto> updatePartialUser(@PathVariable Long id ,
                                                     @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(userService.updatePartialUserById(id,updates));
    }

//    @PostMapping("/api/otp")

}

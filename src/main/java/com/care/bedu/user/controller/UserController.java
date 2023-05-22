package com.care.bedu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.Dto.User;
import com.care.bedu.user.Dto.UserDto;
import com.care.bedu.user.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        // Validate the user input
        if (userDto.getEmail().isEmpty() || userDto.getPassword().isEmpty()) {
            return new ResponseEntity<>("Email and password are required.", HttpStatus.BAD_REQUEST);
        }

        // Create a new user
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());

        // Save the user
        userService.save(newUser);

        return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
    }
}
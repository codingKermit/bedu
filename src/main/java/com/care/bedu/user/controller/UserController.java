package com.care.bedu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.Dto.User;
import com.care.bedu.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	final UserService userService;
//    final PasswordEncoder passwordEncoder;

	    @PostMapping("/api/member")
	    public String saveMember(@RequestBody @Valid User user) throws Exception{
	        return "success";
	    }

}
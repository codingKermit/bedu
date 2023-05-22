package com.care.bedu.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.dto.MemberDto;
import com.care.bedu.user.entity.MemberEntity;
import com.care.bedu.user.entity.MemberRepository;
import com.care.bedu.user.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid MemberDto memberDto) {
        MemberEntity savedUser = memberService.register(memberDto);
        if (savedUser != null) {
            return ResponseEntity.ok("회원가입 성공");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/checkEmailAvailability")
    public ResponseEntity<Boolean> checkEmailAvailability(@RequestBody String email) {
        boolean isAvailable = !memberService.checkEmailAvailability(email);
        return ResponseEntity.ok(isAvailable);
    }
}

package com.care.bedu.user.controller;

import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.config.JwtUtil;
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

	private final AuthenticationManager authenticationManager;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> paramMap) {
        String email = paramMap.get("email");
        String password = paramMap.get("password");

        UserDetails loginUser = memberService.loadUserByUsername(email); //email로 정보 가져오기

        Authentication authentication = authenticationManager.authenticate(     //가져온 정보와 입력한 비밀번호로 검증
                new UsernamePasswordAuthenticationToken(loginUser, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);   // 검증 통과 후 authentication 세팅

        String accessToken = jwtUtil.createToken(loginUser.getUsername(), loginUser.getUsername());     //accessToken 생성

        Map<String, Object> result = new HashMap<>();
        result.put("email", loginUser.getUsername());
        result.put("user_token", accessToken);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid MemberDto memberDto) {

        String encPassword = passwordEncoder.encode(memberDto.getPassword());
	    MemberEntity memberEntity = MemberEntity.builder()
	            .email(memberDto.getEmail())
	            .password(encPassword)
	            .nickname(memberDto.getNickname())
	            .cls("USER") 
	            .udy(0) 
	            .regDate(LocalDateTime.now()) // regDate 값을 현재 시간으로 설정
	            .userRegDate(LocalDate.now()) // userRegDate 값을 현재 날짜로 설정
	            .build();

	    MemberEntity savedUser = memberRepository.save(memberEntity);
	    if (savedUser != null) {
	        return ResponseEntity.ok("회원가입 성공");
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}
    
    @GetMapping("/register/emil/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        boolean isDuplicate = memberService.checkEmailDuplicate(email);
        return ResponseEntity.ok(isDuplicate);
    }
    
    @GetMapping("/register/nickname/{nickname}")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
    	 boolean isDuplicate = memberService.checkNicknameDuplicate(nickname);
         return ResponseEntity.ok(isDuplicate);
    }
}

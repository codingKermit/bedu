package com.care.bedu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.service.MemberService;
import com.care.bedu.user.vo.MemberVO;

@RestController
@RequestMapping("/api")
public class MemberController {
    
    private final MemberService memberService;
    
    @Autowired
    public MemberController(MemberService memberService) {
    	this.memberService = memberService;
    }

	// 회원 가입 요청
    @PostMapping("/register")
    public ResponseEntity<String> register(MemberVO memberVo) {
        memberService.register(memberVo);

        return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.CREATED);
    }
	
	// 이메일 중복 체크
    @GetMapping("/register/email/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        boolean isDuplicate = memberService.isEmailDuplicate(email);
        return ResponseEntity.ok(isDuplicate);
    }

    // 닉네임 중복 체크
    @GetMapping("/register/nickname/{nickname}")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
        boolean isDuplicate = memberService.isNicknameDuplicate(nickname);
        return ResponseEntity.ok(isDuplicate);
    }

}

package com.care.bedu.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.bedu.user.dto.MemberDto;
import com.care.bedu.user.entity.MemberEntity;
import com.care.bedu.user.entity.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberEntity register(MemberDto memberDto) {
        String encPassword = passwordEncoder.encode(memberDto.getPassword());
        MemberEntity memberEntity = MemberEntity.builder()
                .email(memberDto.getEmail())
                .password(encPassword)
                .nickname(memberDto.getNickname())
                .build();
        return memberRepository.save(memberEntity);
    }
    
    public boolean checkEmailDuplicate(String email) {
    	return memberRepository.existsByEmail(email);
    }
    
    public boolean checkNicknameDuplicate(String nickname) {
    	return memberRepository.existsByNickname(nickname);
    }
}
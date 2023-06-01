package com.care.bedu.user.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.bedu.user.mapper.MemberMapper;
import com.care.bedu.user.vo.MemberVo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Transactional
    public void regist(MemberVo memberVo){
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVo.setPassword(passwordEncoder.encode(memberVo.getPassword()));
        memberVo.setEmail(memberVo.getEmail());
        memberVo.setNickname(memberVo.getNickname());
        memberVo.setCls("USER");
        memberVo.setUdy(0);
        memberVo.setRegDate(LocalDateTime.now());
        memberVo.setUserRegDate(LocalDate.now());
        memberMapper.regist(memberVo);
    }
    
    // 이메일 중복 체크
    public boolean isEmailDuplicate(String email) {
        int count = memberMapper.countByEmail(email);
        return count > 0;
    }

    // 닉네임 중복 체크
    public boolean isNicknameDuplicate(String nickname) {
        int count = memberMapper.countByNickname(nickname);
        return count > 0;
    }
}

package com.care.bedu.user.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.bedu.user.dao.memberDAO;
import com.care.bedu.user.service.MemberService;
import com.care.bedu.user.vo.memberVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class memberServiceImpl implements MemberService {
    private final memberDAO memberDao;

    @Transactional
    public void regist(memberVO memberVo) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVo.setPassword(passwordEncoder.encode(memberVo.getPassword()));
        memberVo.setEmail(memberVo.getEmail());
        memberVo.setNickname(memberVo.getNickname());
        memberVo.setCls("USER");
        memberVo.setUdy(0);
        memberVo.setRegDate(LocalDateTime.now());
        memberVo.setUserRegDate(LocalDate.now());
        memberDao.regist(memberVo);
    }

    public boolean isEmailDuplicate(String email) {
        int count = memberDao.countByEmail(email);
        return count > 0;
    }

    public boolean isNicknameDuplicate(String nickname) {
        int count = memberDao.countByNickname(nickname);
        return count > 0;
    }
}
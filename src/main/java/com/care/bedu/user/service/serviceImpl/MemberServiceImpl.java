package com.care.bedu.user.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.bedu.user.dao.MemberDAO;
import com.care.bedu.user.service.MemberService;
import com.care.bedu.user.vo.MemberVO;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDao, BCryptPasswordEncoder passwordEncoder) {
    	this.memberDao = memberDao;
    	this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    @Override
    public void register(MemberVO memberVo) {
    	memberVo.setPassword(passwordEncoder.encode(memberVo.getPassword()));
        memberVo.setEmail(memberVo.getEmail());
        memberVo.setNickname(memberVo.getNickname());
        memberVo.setRegDate(LocalDateTime.now());
        memberVo.setCls("USER");
        memberVo.setUrd(LocalDate.now());
        memberVo.setUdy(0);
        memberDao.register(memberVo);
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
package com.care.bedu.user.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.user.dao.MemberDAO;
import com.care.bedu.user.service.MemberService;
import com.care.bedu.user.vo.MemberVO;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDao;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDao) {
    	this.memberDao = memberDao;
    }
    
    @Transactional
    @Override
    public void register(MemberVO memberVo) {
    	byte[] password = memberVo.getPassword().getBytes();
    	String encodePwd= Base64.getEncoder().encodeToString(password);
        memberVo.setPassword(encodePwd);
        memberVo.setEmail(memberVo.getEmail());
        memberVo.setNickname(memberVo.getNickname());
        memberVo.setRegDate(LocalDateTime.now());
        memberVo.setCls("USER");
        memberVo.setUrd(LocalDate.now());
        memberVo.setUdy("N");
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
    
    public String getPasswordByEmail(String email) {
        return memberDao.getPasswordByEmail(email);
    }
    
    public MemberVO getMemberByEmail(String email) {
        return memberDao.getMemberByEmail(email);
    }
  
    @Override
    public List<Integer> getLikedBoardNumbersByEmail(String email) {
        return memberDao.getLikedBoardNumbersByEmail(email);
    }

}
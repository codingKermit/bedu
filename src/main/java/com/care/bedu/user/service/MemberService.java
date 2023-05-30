package com.care.bedu.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.care.bedu.user.entity.MemberEntity;
import com.care.bedu.user.entity.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService{
	
    private final MemberRepository memberRepository;
    
    public boolean checkEmailDuplicate(String email) {
    	return memberRepository.existsByEmail(email);
    }
    
    public boolean checkNicknameDuplicate(String nickname) {
    	return memberRepository.existsByNickname(nickname);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	List<GrantedAuthority> authorities = new ArrayList<>();

    	MemberEntity memberEntity = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));


        return new User(memberEntity.getEmail(), memberEntity.getPassword(), authorities);
    }
}
package com.care.bedu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.care.bedu.user.security.JwtUtil;


@SpringBootTest
public class UserTokenTest {
	    @Autowired
	    JwtUtil jwtUtil;

	    @DisplayName("1. 토큰 생성 후 검증")
	    @Test
	    void test_1(){
	        String email = "sss@sss.sss";
	        String nickname = "sss";

	        String token = jwtUtil.createToken(email, nickname, 0);

	        System.out.println("Token : " + token);

	        assertThat(jwtUtil.decodeToken(token).getClaim("nickname").asString()).isEqualTo(nickname);
	    }
	}
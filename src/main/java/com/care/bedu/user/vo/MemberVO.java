package com.care.bedu.user.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberVO {

	private int usernum;
	
	private String email;
	
	private String password;
	
	private String nickname;
	
	private LocalDateTime regDate;
	
	private String cls;
	
	private LocalDate urd;
	
	private String udy;
	
	private int cbnum;
	
}

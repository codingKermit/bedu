package com.care.bedu.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

	private String email;
	private String password;
	private String nickname;
	private LocalDateTime regDate;
	private String cls;
	private LocalDate userRegDate;
	private int udy;
	
	public MemberDto(String email, String password, String nickname, LocalDateTime regDate, String cls, LocalDate userRegDate, int udy) {

		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.regDate = regDate;
		this.cls = cls;
		this.userRegDate = userRegDate;
		this.udy = udy;
	}
}

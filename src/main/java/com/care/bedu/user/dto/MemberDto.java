package com.care.bedu.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

	private String email;
	private String password;
	private String nickname;
	
	public MemberDto(String email, String password, String nickname) {

		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}
}

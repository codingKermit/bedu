package com.care.bedu.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	
	private String email;
	private String password;
	
	public MemberDto(String email, String password) {

		this.email = email;
		this.password = password;
	}
}

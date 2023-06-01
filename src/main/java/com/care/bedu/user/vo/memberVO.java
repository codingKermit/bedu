package com.care.bedu.user.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class memberVO {

	private Long usernum;
	private String email;
	private String password;
	private String nickname;
	private LocalDateTime regDate;
	private String cls;
	private LocalDate userRegDate;
	private int udy;
	
}

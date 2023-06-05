package com.care.bedu.user.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bedu_db")
public class MemberVO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_NUM")
	private Long usernum;
	
	@Column(name = "USER_ID")
	private String email;
	
	@Column(name = "USER_PW")
	private String password;
	
	@Column(name = "USER_NAME")
	private String nickname;
	
	@Column(name = "REG_DATE")
	private LocalDateTime regDate;
	
	@Column(name = "USER_CLS")
	private String cls;
	
	@Column(name = "USER_REG_DATE")
	private LocalDate userRegDate;
	
	@Column(name = "USER_WITH_DRAW_YN")
	private int udy;
	
}

package com.care.bedu.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.care.bedu.user.dto.MemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="t_bedu_user")
@Entity
public class MemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_NUM")
	private Long usernum;
	
	@Column(name = "REG_DATE")
	private LocalDateTime regDate;
	
	@Column(name = "USER_CLS")
	private String cls;
	
	@Column(name = "USER_ID")
	private String email;
	
	@Column(name = "USER_PW")
	private String password;
	
	@Column(name = "USER_NAME")
	private String nickname;

	@Column(name = "USER_REG_DATE")
	private LocalDate userRegDate;

	@Column(name = "USER_WITH_DRAW_YN")
	private int udy;

	public MemberEntity(MemberDto memberDto) {
		this.email = memberDto.getEmail();
		this.password = memberDto.getPassword();
		this.nickname = memberDto.getNickname();
		this.regDate = memberDto.getRegDate();
		this.cls = memberDto.getCls();
		this.userRegDate = memberDto.getUserRegDate();
		this.udy = memberDto.getUdy();
	}
	
	  public static MemberEntity registMember(MemberDto memberDto){
	        return new MemberEntity(memberDto);
	    }
	
}

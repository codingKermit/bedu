package com.care.bedu.user.entity;

import com.care.bedu.user.dto.MemberDto;

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
@Table(name="userinfo")
@Entity
public class MemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usernum;
	private String email;
	private String password;
	private String nickname;

	public MemberEntity(MemberDto memberDto) {
		this.email = memberDto.getEmail();
		this.password = memberDto.getPassword();
		this.nickname = memberDto.getNickname();
	}
	
	  public static MemberEntity registMember(MemberDto memberDto){
	        return new MemberEntity(memberDto);
	    }
	
}

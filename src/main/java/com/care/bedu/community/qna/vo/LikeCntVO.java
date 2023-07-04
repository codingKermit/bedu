package com.care.bedu.community.qna.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LikeCntVO {
	
	private int likeNum;	//글번호
	private String regId;	// 타 테이블에서 구분
	private String userName;	//작성자닉네임
	private int qsBdNum;
	private int commBdNum;
	private Date regDate;	//게시판 시간
	private Boolean likeyn;
	
	
}

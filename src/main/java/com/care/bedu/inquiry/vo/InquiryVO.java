package com.care.bedu.inquiry.vo;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryVO {

	private int vocNum; // 글번호
	private String title; // 제목
	private String content; // 내용
	private String fileName; // .파일 왼쪽 부분
	private String fileType; // .파일 우측 부분
	private String userName; // 유저 이름
	private Date regDate; // 문의 시간
	private String regId; // 타 테이블에서 구분
	
}

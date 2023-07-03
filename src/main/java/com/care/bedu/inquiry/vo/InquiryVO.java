package com.care.bedu.inquiry.vo;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryVO {
	
	private int voc_num; //글번호
	private String title; //제목
	private String content; //내용
	//private String FILE_NAME; //.파일 왼쪽 부분
	//private String FILE_TYPE; //.파일 우측 부분
	private String USER_NAME; // 유저 이름
	private String reg_date;	//문의 시간
	private String reg_id;	// 타 테이블에서 구분
}

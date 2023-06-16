package com.care.bedu.community.qna.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QnaVO {					//질문답변게시판 객체(qna)
	
	private Integer qna_bd_num;	//글번호
	private int page;	//글시작번호
	private int limit;	//글끝번호
	private String reg_id;	// 타 테이블에서 구분
	private String user_id;	//작성자
	private String content;	//내용
	private Date qna_date;	//작성시간
	private Date reg_date;	//게시판 시간
	private String str_qna_date;
	private String str_reg_date;
	private String title;	//제목
	private Integer qna_cnt;	//조회수
	private Integer qna_like_yn;	//좋아요
	private String keyword;		//검색 키워드

}

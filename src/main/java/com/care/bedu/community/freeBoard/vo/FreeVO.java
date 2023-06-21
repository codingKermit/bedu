package com.care.bedu.community.freeBoard.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FreeVO {
	
	private Integer comm_num;	//글번호
	private int page;	//페이지번호
	private int limit;
	private String reg_id;	// 타 테이블에서 구분
	private String user_name;	//작성자
	private String content;	//내용
	private Date comm_date;	//작성시간
	private Date reg_date;	//게시판 시간
	private String title;	//제목
	private Integer comm_cnt;	//조회수
	private Integer comm_like_yn;	//좋아요
	private String keyword;		//검색 키워드
	private String str_comm_date;
	private String str_reg_date;

}

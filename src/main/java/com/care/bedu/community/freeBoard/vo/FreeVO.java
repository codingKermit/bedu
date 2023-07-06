package com.care.bedu.community.freeBoard.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FreeVO {
	
	private Integer commNum;	//글번호
	private int page;	//글시작번호
	private int limit;	//글끝번호
	private String regId;	// 타 테이블에서 구분
	private String userName;	//작성자
	private String userId;		//user회원테이블의 유저 아이디
	private String content;	//내용
	private String userNum;		//회원글번호
	private Date commDate;	//작성시간
	private Date regDate;	//게시판 시간
	private String title;	//제목
	private Integer commCnt;	//조회수
	private Integer commLikeYn;	//좋아요
	private String keyword;		//검색 키워드
	private String strCommDate;	//표준시간변환날짜
	private String strRegDate;

}

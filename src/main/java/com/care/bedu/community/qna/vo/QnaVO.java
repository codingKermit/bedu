package com.care.bedu.community.qna.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QnaVO {					//질문답변게시판 객체(qna)
	
	private Integer qnaBdNum;	//글번호
	private String title;	//제목
	private String content;	//내용
	private String userName;	//작성자닉네임
	private String regId;	// 타 테이블에서 구분
	private String userId;	//user회원테이블의 유저 아이디
	private String userNum; //회원글번호
	private Date qnaDate;	//작성시간
	private Date regDate;	//게시판 시간
	private String strQnaDate;
	private String strRegDate;
	private Integer qnaCnt;	//조회수
	private Integer qnaLikeCnt;	//좋아요
	private String keyword;		//검색 키워드
	
	private int page;	//글시작번호
	private int limit;	//글끝번호

}

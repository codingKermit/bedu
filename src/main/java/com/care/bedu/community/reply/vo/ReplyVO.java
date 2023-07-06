package com.care.bedu.community.reply.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReplyVO{					//댓글
	
	private Integer replyNum;			//글번호
	private String userName;			//작성자
	private String content;				//내용
	private Date replyDate;			//작성날짜
	private Date regDate;			//수정날짜
	private Integer commNum;		//자유글번호
	private Integer qsNum;				//질문글번호
	private Integer ansNum;				//답변글번호
	private Integer rwNum;				//강의후기글번호
	private Integer lecturenum;			
	private String strReplyDate;		
	private String strRegDate;
	private String regId;					//등록아이디

}

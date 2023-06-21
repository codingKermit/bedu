package com.care.bedu.community.reply.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReplyVO{					//댓글
	
	private Integer replyNum;
	private String userName;
	private String content;
	private Date replyDate;
	private Date regDate;
	private Integer commNum;
	private Integer qsNum;
	private Integer ansNum;
	private Integer rwNum;
	private Integer lecturenum;
	private String strReplyDate;
	private String strRegDate;
	private String regId;

}

package com.care.bedu.community.reply.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReplyVO{					//댓글
	
	private Integer replyNum;
	private String userId;
	private String content;
	private Date replyDate;
	private Integer commNum;
	private Integer faqNum;
	private Integer rwNum;
	private Integer lecturenum;
	private String strReplyDate;
	private String strRegDate;
	private Date regDate;
	private String regId;

}

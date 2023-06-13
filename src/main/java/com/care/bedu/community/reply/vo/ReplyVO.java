package com.care.bedu.community.reply.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReplyVO{					//댓글
	
	private Integer replyNum;
	private String userId;
	private String content;
	private String date;
	private Integer commNum;
	private Integer faqNum;
	private Integer rwNum;
	private Integer lecturenum;
	private String regDate;
	private String regId;

}

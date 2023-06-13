package com.care.bedu.community.ans.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AnsVO{						//답글
	
	private Integer ansBdNum;
	private String userId;
	private String content;
	private String ansDate;
	private Integer qsBdNum;
	private Integer ansLikeCnt;
	private String regDate;
	private String regId;

}

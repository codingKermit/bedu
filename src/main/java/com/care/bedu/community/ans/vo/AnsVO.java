package com.care.bedu.community.ans.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AnsVO{						//답글
	
	private Integer ansBdNum;
	private String userId;
	private String content;
	private Date ansDate;
	private Integer qsBdNum;
	private Integer ansLikeCnt;
	private Date regDate;
	private String regId;
	private String strAnsDate;
	private String strRegDate;

}

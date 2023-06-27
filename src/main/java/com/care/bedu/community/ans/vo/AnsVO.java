package com.care.bedu.community.ans.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AnsVO{						//답글
	
	private Integer ansBdNum;
	private String userName;
	private String content;
	private Date ansDate;
	private int qsBdNum;
	private Integer ansLikeCnt;
	private Date regDate;
	private String regId;
	private String strAnsDate;
	private String strRegDate;

}

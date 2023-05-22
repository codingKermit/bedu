package com.care.bedu.community.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommunityDTO {
	
	private Integer num;
	private int page;
	private String userId;
	private String writer;
	private String contents;
	private String writeDate;
	private String title;
	private String views;
	private String keyword;

}

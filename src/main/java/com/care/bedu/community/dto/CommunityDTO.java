package com.care.bedu.community.dto;

import lombok.Data;

@Data
public class CommunityDTO {
	
	private int num;
	private String userId;
	private String writer;
	private String contents;
	private String writeDate;
	private String title;
	private String views;

}

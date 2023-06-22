package com.care.bedu.user.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AgreeVO {

	private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

}

package com.care.bedu.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDetailDto {

	private int num;
	private int postId;
	private String title;
	private int times;
}

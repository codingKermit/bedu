package com.care.bedu.lecture.dto;

import java.sql.Time;

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

	private int lect_dtl_num;
	private int lect_num;
	private String lect_dtl_title;
	private Time times;
}

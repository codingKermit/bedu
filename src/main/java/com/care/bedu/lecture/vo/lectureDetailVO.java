package com.care.bedu.lecture.vo;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureDetailVO {

	private int lectDtlNum;
	private int lectNum;
	private String lectDtlTitle;
	private Time times;
}

package com.care.bedu.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureDto {
	private int num; // 강의 고유번호
	private String title; // 강의 이름
	private String teacher; // 강사명
	private int total; // 강의 갯수
	private int duration; // 수강 기간
	private String thumbnail; // 썸네일 이미지 경로
	private int view; // 조회수
	private String regiDate; // 강의 개설일
	private String price; // 가격
	private String description; // 강의 상세정보
	private boolean likeYn; // 유저별 강의 좋아요 여부
	private double score; // 강의 평점
	private int scoreUsers; // 강의 평점 매긴 사람
	private int totalTimes; // 총 강의 시간
	private String category; // 강의 카테고리
	//또 뭐있어야하지?

}

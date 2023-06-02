package com.care.bedu.lecture.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/* 대,중,소 분류에 사용할 VO */
public class LectureCategoriesVO {
    int num; // 고유 번호
    int cateCode; // 고유 코드
    String cateKor; // 카테고리의 한글명
    int parentNum; // 부모 카테고리의 고유 번호
    int parentCode; // 부모 카테고리의 고유 코드
    String icon; // 대분류의 아이콘 파일명
}

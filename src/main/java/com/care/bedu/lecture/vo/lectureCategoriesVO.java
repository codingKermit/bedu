package com.care.bedu.lecture.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCategoriesVO {
    int num;
    int cateCode;
    String cateKor;
    int parentNum;
    int parentCode;
    String icon;
}

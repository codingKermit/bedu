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
public class LectureCategoriesDto {
    int num;
    int cate_code;
    String cate_kor;
    int parent;
    String thumbnail;
    // String top_cate;
    // String top_cate_kor;
    // String top_cate_icon;
    // String mid_cate;
    // String mid_cate_kor;
    // String bot_cate;
    // String bot_cate_kor;
}

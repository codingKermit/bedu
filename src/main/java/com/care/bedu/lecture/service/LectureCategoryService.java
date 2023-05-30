package com.care.bedu.lecture.service;

import java.util.ArrayList;

import com.care.bedu.lecture.dto.LectureCategoriesDto;

public interface LectureCategoryService {
    
    public ArrayList<LectureCategoriesDto> getCategory(); /* 대중소 카테고리 전체 조회 */
    public ArrayList<LectureCategoriesDto> getTop(); /* 대분류 조회 */
}

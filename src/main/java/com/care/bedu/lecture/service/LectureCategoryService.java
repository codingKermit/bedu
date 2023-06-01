package com.care.bedu.lecture.service;

import java.util.ArrayList;

import com.care.bedu.lecture.vo.lectureCategoriesVO;

public interface lectureCategoryService {
    
    public ArrayList<lectureCategoriesVO> getCategory(); /* 대중소 카테고리 전체 조회 */
    public ArrayList<lectureCategoriesVO> getTop(); /* 대분류 조회 */
}

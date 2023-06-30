package com.care.bedu.lecture.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.care.bedu.lecture.vo.LectureCategoriesVO;

public interface LectureCategoryService {
    
    public ArrayList<LectureCategoriesVO> getCategory(); /* 대중소 카테고리 전체 조회 */
    public HashMap<String, Object> getCategoryForLevel();
}

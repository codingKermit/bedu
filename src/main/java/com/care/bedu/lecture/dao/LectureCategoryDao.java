package com.care.bedu.lecture.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.vo.LectureCategoriesVO;

@Mapper
public interface LectureCategoryDAO {
    public ArrayList<LectureCategoriesVO> getCategories();
    public ArrayList<LectureCategoriesVO> getBot(String mid);
    public ArrayList<LectureCategoriesVO> getTop();
}

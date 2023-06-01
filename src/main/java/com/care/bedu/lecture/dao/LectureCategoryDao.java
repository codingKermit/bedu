package com.care.bedu.lecture.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.vo.lectureCategoriesVO;

@Mapper
public interface lectureCategoryDAO {
    public ArrayList<lectureCategoriesVO> getCategories();
    public ArrayList<lectureCategoriesVO> getBot(String mid);
    public ArrayList<lectureCategoriesVO> getTop();
}

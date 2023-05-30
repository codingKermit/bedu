package com.care.bedu.lecture.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.dto.LectureCategoriesDto;

@Mapper
public interface LectureCategoryDao {
    public ArrayList<LectureCategoriesDto> getCategories();
    public ArrayList<LectureCategoriesDto> getBot(String mid);
    public ArrayList<LectureCategoriesDto> getTop();
}

package com.care.bedu.lecture.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.vo.LectureFildVO;

/**
 * CategoriesFildDAO
 */
@Mapper
public interface LectureFildDAO {

    public ArrayList<LectureFildVO> getList();
    
}
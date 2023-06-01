package com.care.bedu.lecture.service.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.lecture.dao.lectureCategoryDAO;
import com.care.bedu.lecture.service.lectureCategoryService;
import com.care.bedu.lecture.vo.lectureCategoriesVO;

@Service
public class lectureCategoryServiceImpl implements lectureCategoryService{

    @Autowired
    lectureCategoryDAO dao;

    @Override
    public ArrayList<lectureCategoriesVO> getCategory() {
        ArrayList<lectureCategoriesVO> list = new ArrayList<>();
        list = dao.getCategories();
        return list;
    }

    @Override
    public ArrayList<lectureCategoriesVO> getTop() {
        ArrayList<lectureCategoriesVO> tops = new ArrayList<>();

        tops = dao.getTop();

        return tops;
    }
    
}

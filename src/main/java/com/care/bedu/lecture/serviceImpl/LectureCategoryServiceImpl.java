package com.care.bedu.lecture.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.lecture.dao.LectureCategoryDao;
import com.care.bedu.lecture.dto.LectureCategoriesDto;
import com.care.bedu.lecture.service.LectureCategoryService;

@Service
public class LectureCategoryServiceImpl implements LectureCategoryService{

    @Autowired
    LectureCategoryDao dao;

    @Override
    public ArrayList<LectureCategoriesDto> getCategory() {
        // TODO Auto-generated method stub
        ArrayList<LectureCategoriesDto> list = new ArrayList<>();
        list = dao.getCategories();
        return list;
    }

    @Override
    public ArrayList<LectureCategoriesDto> getTop() {
        // TODO Auto-generated method stub
        ArrayList<LectureCategoriesDto> tops = new ArrayList<>();

        tops = dao.getTop();

        return tops;
    }
    
}

package com.care.bedu.lecture.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.dto.LectureCategoriesDto;
import com.care.bedu.lecture.serviceImpl.LectureCategoryServiceImpl;

@RestController
@RequestMapping("/api")
public class LectureCategoryController {

    @Autowired
    LectureCategoryServiceImpl service;

    @RequestMapping("/getCategory")
    public ArrayList<LectureCategoriesDto> getCategory(){
        ArrayList<LectureCategoriesDto> list = new ArrayList<>();

        list = service.getCategory();

        return list;

    }
    
}

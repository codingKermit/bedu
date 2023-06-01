package com.care.bedu.lecture.controller;

import java.util.ArrayList;
import java.util.HashMap;

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

    /* 대중소 카테고리 전체 조회 */
    @RequestMapping("/getCategory")
    public ArrayList<LectureCategoriesDto> getCategory(){
        ArrayList<LectureCategoriesDto> list = new ArrayList<>();

        list = service.getCategory();
        return list;
    }

    /* 대분류 카테고리만 조회 */
    @RequestMapping("/getTop")
    public HashMap<String, Object> getTop(){
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<LectureCategoriesDto> tops = new ArrayList<>();

        tops = service.getTop();

        map.put("item", tops);

        return map;
    }
    
}

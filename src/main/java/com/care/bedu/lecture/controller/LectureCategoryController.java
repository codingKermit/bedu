package com.care.bedu.lecture.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.service.serviceImpl.lectureCategoryServiceImpl;
import com.care.bedu.lecture.vo.lectureCategoriesVO;



@RestController
@RequestMapping("/api")
public class lectureCategoryController {

    @Autowired
    lectureCategoryServiceImpl lectCateservice;

    /* 대중소 카테고리 전체 조회 */
    @RequestMapping("/getCategory")
    public ArrayList<lectureCategoriesVO> getCategory(){
        ArrayList<lectureCategoriesVO> list = new ArrayList<>();

        list = lectCateservice.getCategory();
        return list;
    }

    /* 대분류 카테고리만 조회 */
    @RequestMapping("/getTop")
    public HashMap<String, Object> getTop(){
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<lectureCategoriesVO> tops = new ArrayList<>();

        tops = lectCateservice.getTop();

        map.put("item", tops);

        return map;
    }
    
}

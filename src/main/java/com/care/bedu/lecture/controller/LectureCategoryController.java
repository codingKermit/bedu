package com.care.bedu.lecture.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.service.serviceImpl.LectureCategoryServiceImpl;
import com.care.bedu.lecture.vo.LectureCategoriesVO;



@RestController
@RequestMapping("/api")
public class LectureCategoryController {

    @Autowired
    LectureCategoryServiceImpl lectCateService;

    /* 대중소 카테고리 전체 조회 */
    @RequestMapping("/getCategory")
    public ArrayList<LectureCategoriesVO> getCategory(){
        ArrayList<LectureCategoriesVO> list = new ArrayList<>();

        list = lectCateService.getCategory();
        return list;
    }

    /* 대분류 카테고리만 조회 */
    @RequestMapping("/getTop")
    public HashMap<String, Object> getTop(){
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<LectureCategoriesVO> tops = new ArrayList<>();

        tops = lectCateService.getTop();

        map.put("item", tops);

        return map;
    }
    
}

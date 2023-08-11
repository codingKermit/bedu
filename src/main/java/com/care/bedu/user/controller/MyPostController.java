package com.care.bedu.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.service.MyPostService;

@RestController
@RequestMapping("/api/mypost")
public class MyPostController {
    
    @Autowired
    MyPostService service;

    @RequestMapping("/getList")
    public Map<String,Object> getList(String userName, String order, String group){
        return service.getList(userName, order, group);
    }

}
package com.care.bedu.lecture.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.service.serviceImpl.LectureFildServiceImpl;

@RestController
@RequestMapping("/api/lect/fild")
public class LectureFildController {

    @Autowired
    LectureFildServiceImpl service;

    @RequestMapping("/getList")
    public HashMap<String, Object> getList(){
        return service.getList();
    }

    
}

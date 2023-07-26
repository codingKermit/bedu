package com.care.bedu.lecture.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.service.serviceImpl.LectureHistoryServiceImpl;

@RestController
@RequestMapping("/api/lecture/history")
public class LectureWatchController {
    
    @Autowired
    LectureHistoryServiceImpl service;

    @RequestMapping("/save")
    public void watchHistorySave(String userName, int lectDtlNum, String endTime){
        service.watchHistorySave(userName, lectDtlNum, endTime);
    }
}

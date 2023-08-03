package com.care.bedu.lecture.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.lecture.service.serviceImpl.LectureHistoryServiceImpl;
import com.care.bedu.lecture.vo.LectureHistoryVO;

@RestController
@RequestMapping("/api/lecture/history")
public class LectureWatchController {
    
    @Autowired
    LectureHistoryServiceImpl service;

    @RequestMapping("/save")
    public void watchHistorySave(String userName, int lectDtlNum, String endTime){
        service.watchHistorySave(userName, lectDtlNum, endTime);
    }

        /* 동영상 재생 정보 조회 */
    @RequestMapping("/getHistory")
    public ResponseEntity<LectureHistoryVO> getHistory(String userName, int lectDtlNum){
        return new ResponseEntity<LectureHistoryVO>(service.getHistory(userName, lectDtlNum), HttpStatus.OK);
    }
}

package com.care.bedu.lecture.service.serviceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.lecture.dao.LectureHistoryDAO;
import com.care.bedu.lecture.service.LectureHistoryService;
import com.care.bedu.lecture.vo.LectureHistoryVO;

@Service
public class LectureHistoryServiceImpl implements LectureHistoryService{

    @Autowired
    LectureHistoryDAO dao;

    @Override
    public int watchHistorySave(String userName, int lectDtlNum, String endTime) {
        LectureHistoryVO vo = new LectureHistoryVO();

        vo.setUserName(userName);
        vo.setLectDtlNum(lectDtlNum);
        vo.setEndTime(Integer.parseInt(endTime));

        return dao.watchHistorySave(vo);
    }
    
}

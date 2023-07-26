package com.care.bedu.lecture.service;

import com.care.bedu.lecture.vo.LectureHistoryVO;

public interface LectureHistoryService {
    
    /* 동영상 재생 정보 저장 & 업데이트  */
    public int watchHistorySave(String userName, int lectDtlNum, String endTime);
}

package com.care.bedu.lecture.dao;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.lecture.vo.LectureHistoryVO;

@Mapper
public interface LectureHistoryDAO {
    
    /* 동영상 재생 정보 저장 & 업데이트  */
    public int watchHistorySave(LectureHistoryVO vo);

}

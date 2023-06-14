package com.care.bedu.statistics.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.statistics.vo.StatisticsVO;

@Mapper
public interface StatisticsDAO {

    // 통계 리스트를 가져오는 메소드
    public List<StatisticsVO> getStatistics(HashMap<String, Object> map);
    
}
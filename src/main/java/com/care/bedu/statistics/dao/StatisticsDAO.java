package com.care.bedu.statistics.dao;

import java.util.HashMap;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsDAO {

    // 통계 리스트를 가져오는 메소드
    HashMap<String, Object> getStatistics(); 
    
}
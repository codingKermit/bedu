package com.care.bedu.statistics.service;

import java.util.HashMap;
import java.util.List;

import com.care.bedu.statistics.dao.StatisticsDAO;




public interface StatisticsService {

    //통계 데이터 가져오기
    List<StatisticsDAO> getStatistics(); 
    
}
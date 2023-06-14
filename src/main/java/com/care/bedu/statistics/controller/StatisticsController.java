package com.care.bedu.statistics.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.statistics.dao.StatisticsDAO;
import com.care.bedu.statistics.service.StatisticsService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

//통계리스트 조회 
@RequestMapping("/statisticsMain")
public List<StatisticsDAO> getStatistics() {
    List<StatisticsDAO> statisticsList = new ArrayList<>();

    statisticsList = statisticsService.getStatistics();

    return statisticsList;
}

}
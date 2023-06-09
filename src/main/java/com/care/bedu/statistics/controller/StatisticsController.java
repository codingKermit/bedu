package com.care.bedu.statistics.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.statistics.service.StatisticsService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

//통계리스트 조회 
    @RequestMapping("/statisticsMain")
    public HashMap<String, Object> getStatistics(){
        HashMap<String, Object> map = new HashMap<>();

        map = statisticsService.getStatistics();


        return map;
    }

}
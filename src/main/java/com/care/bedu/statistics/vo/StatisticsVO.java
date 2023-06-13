package com.care.bedu.statistics.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class StatisticsVO {
    
    private int lectNum;
    private String title;
    private int lectLikeCnt;
    private int price;
    private float statStarAvg;
}
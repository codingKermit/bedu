package com.care.bedu.statistics.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class StatisticsVO {
    
    private int statNum;
    private String statTitle;
    private int statLikeCnt;
    private int statPayAvg;
    private float statStarAvg;
}
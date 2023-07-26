package com.care.bedu.lecture.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureHistoryVO {
    
    private int num;
    private String userName;
    private int lectDtlNum;
    private int startTime;
    private int endTime;
    private int complete;
}

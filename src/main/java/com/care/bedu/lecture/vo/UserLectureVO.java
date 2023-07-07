package com.care.bedu.lecture.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLectureVO {
    
    private int userLectNum;
    private int userNum;
    private int lectNum;
    private Date lectRegDate;
}

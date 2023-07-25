package com.care.bedu.review.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
    private int id;
    private String topCate;
    private String keyword;
    private String title;
    private String userName;
    private String content;
    private int rwGrade;
    private int lectNum;
    private Date rwDate;
    private Date regDate;
    private String regId;
}

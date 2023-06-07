package com.care.bedu.review.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
    private int id;
    private String title;
    private String writer;
    private String content;
    private int star;
    private int lectureNumber;
    private Date reviewDate;
    private Date registrationDate;
    private String registeredUserId;
}

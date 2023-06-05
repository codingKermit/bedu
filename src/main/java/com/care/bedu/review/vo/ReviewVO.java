package com.care.bedu.review.vo;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReviewVO {
    @Column(name = "RW_NUM")
    private int id;
    
    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "USER_NAME")
    private String writer;
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "RW_GRADE")
    private int star;
    
    @Column(name = "LECT_NUM")
    private int lectureNumber;
    
    @Column(name = "RW_DATE")
    private Date reviewDate;
    
    @Column(name = "REG_DATE")
    private Date registrationDate;
    
    @Column(name = "REG_ID")
    private String registeredUserId;
}

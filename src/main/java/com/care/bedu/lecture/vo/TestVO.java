package com.care.bedu.lecture.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestVO {

   private int num;
   private int topCate;
   private String topCateKor;
   private int midCate;
   private String midCateKor;
   private int botCate;
   private String botCateKor;
   private int parentCode;
   private int level;
   private Date regDate;
   private String regId;
   private String icon; 
}
package com.care.bedu.file.vo;

import lombok.Getter;
import lombok.Setter;


import java.util.Date;

import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("fileVO")
public class FileUploadVO {

    private String lectDtlNum;              //강의고유번호
    private String lectDtlTitle;            //강의제목
    private String lectDtlTime;             //강의재생시간
    private int lectNum;                    //강의번호
    private Date regDate;                   //등록날짜
    private String regId;                   //등록일
    private String lectVideoUrl;            //동영상경로

}

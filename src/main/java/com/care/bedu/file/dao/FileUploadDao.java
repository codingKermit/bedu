package com.care.bedu.file.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.file.vo.FileUploadVO;
import com.care.bedu.lecture.vo.LectureVO;


@Mapper
public interface FileUploadDao {

    public int upload(FileUploadVO param);
    public ArrayList<LectureVO> getTotalLecture();
    public int deleteFile(int num);
}

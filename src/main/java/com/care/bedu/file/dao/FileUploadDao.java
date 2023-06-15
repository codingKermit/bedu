package com.care.bedu.file.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.file.vo.FileUploadVO;


@Mapper
public interface FileUploadDao {

    public int upload(FileUploadVO vo);
    public ArrayList<FileUploadVO> getLectureList(String keyword);
}

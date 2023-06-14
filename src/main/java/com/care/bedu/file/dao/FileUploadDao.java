package com.care.bedu.file.dao;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.file.vo.FileUploadVO;


@Mapper
public interface FileUploadDao {

    public int upload(FileUploadVO param);
}

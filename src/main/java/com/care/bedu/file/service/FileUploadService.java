package com.care.bedu.file.service;

import com.care.bedu.file.vo.FileUploadVO;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public int upload(MultipartFile file);
    public HashMap<String, Object> getLectureList(String keyword);
}

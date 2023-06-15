package com.care.bedu.file.service;


import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public boolean upload(MultipartFile file);
    public HashMap<String, Object> getLectureList(String keyword);
}

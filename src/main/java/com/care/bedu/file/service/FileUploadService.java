package com.care.bedu.file.service;


import java.util.HashMap;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public boolean upload(MultipartFile[] uploadFile, Model model);
    public HashMap<String, Object> getLectureList(String keyword);
}

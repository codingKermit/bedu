package com.care.bedu.file.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface FileUploadService {

    public boolean upload(Model model, int chunkNumber, int totalChunk, MultipartFile file);
    public HashMap<String, Object> getLectureList(String keyword);
}

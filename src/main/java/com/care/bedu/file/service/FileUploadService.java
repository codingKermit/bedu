package com.care.bedu.file.service;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface FileUploadService {

    public boolean upload(int chunkNumber, int totalChunk, MultipartFile file) throws IOException;
    public HashMap<String, Object> getLectureList(String keyword);
    public HashMap<String, Object> getTotalLecture();
}

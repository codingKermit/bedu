package com.care.bedu.file.service;


import java.io.IOException;
import java.util.HashMap;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public boolean upload(MultipartFile file, int chunkNumber, int totalChunks) throws IOException;
    public HashMap<String, Object> getTotalLecture();
}

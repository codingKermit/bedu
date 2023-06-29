package com.care.bedu.admin.fileManage.service;


import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.care.bedu.admin.fileManage.vo.FileUploadVO;


public interface FileUploadService {

    public boolean upload(MultipartFile file, int chunkNumber, int totalChunks, FileUploadVO vo) throws IOException;
    public HashMap<String, Object> getTotalLecture();
    public boolean deleteFile(int num);
}

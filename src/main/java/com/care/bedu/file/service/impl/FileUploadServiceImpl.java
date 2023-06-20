package com.care.bedu.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.bedu.file.dao.FileUploadDao;
import com.care.bedu.file.service.FileUploadService;
import com.care.bedu.file.vo.FileUploadVO;
import com.care.bedu.lecture.vo.LectureVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService{

    FileUploadDao dao;

    @Override
    public boolean upload(int chunkNumber, int totalChunk, MultipartFile file) throws IOException{
        // 파일 업로드 위치
        String uploaddir = "video"; 

        

        File dir = new File(uploaddir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 임시 저장 파일 이름
        String filename = file.getOriginalFilename() +".part" + chunkNumber; 

        Path filepath = Paths.get(filename, uploaddir);
        // 파일 임시저장
            Files.write(filepath, file.getBytes());

        if(chunkNumber == totalChunk - 1){
            String[] split = file.getOriginalFilename().split("\\.");
            String outputfilename = UUID.randomUUID() + "." + split[split.length-1];
            Path outputfile = Paths.get(uploaddir, filename);
                Files.createFile(outputfile);

        for(int i = 0; i < chunkNumber; i++){
            Path chunkFile = Paths.get(uploaddir, file.getOriginalFilename() + ".path" + i);
            Files.write(outputfile, Files.readAllBytes(chunkFile), StandardOpenOption.APPEND);
                // 합친 후 삭제
            Files.delete(chunkFile);
        }
        log.info("File upload successfully");
        return true;

        }else{
            return false;
        }
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
    

    @Override
    public HashMap<String, Object> getLectureList(String keyword) {
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<LectureVO> list = new ArrayList<>();

        list = dao.getLectureList(keyword);

        map.put("item", list);

        return map;
    }

   
}

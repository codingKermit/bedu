package com.care.bedu.admin.lectureManage.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.bedu.admin.lectureManage.service.LectureManageService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class LectureManageServiceImpl implements LectureManageService{

    private String baseUrl = "C:/Desktop/IMG/LECT_MANAGE/";

    @Override
    public HashMap<String, Object> imgUpload(MultipartHttpServletRequest req, HttpServletResponse res) throws IOException {

        HashMap<String, Object> map = new HashMap<>();

        MultipartFile multiFile = req.getFile("upload");

        String extention = multiFile.getOriginalFilename().split("\\.")[1];
        
        String uid = UUID.randomUUID().toString() + "." + extention;
        String uploadUrl = baseUrl+ uid;
        
        File file = new File(uploadUrl);

        InputStream input = multiFile.getInputStream();
        OutputStream output = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        
        while(input.read(bytes) != -1){
            output.write(bytes);
        }

        output.close();
        input.close();
        output.flush();

        map.put("url", "http://172.30.1.85:8081/IMG/LECT_MANAGE/"+uid);


        return map;
    }
    
}

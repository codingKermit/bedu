package com.care.bedu.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.care.bedu.file.dao.FileUploadDao;
import com.care.bedu.file.service.FileUploadService;
import com.care.bedu.file.vo.FileUploadVO;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService{

    private String fileBaseDir = "C:/Desktop/";

    @Autowired
    private FileUploadDao dao;

    @Override
    public int upload(MultipartFile file) {
        if(file.isEmpty()) {
            return -1;
        }

        long time = System.currentTimeMillis();

        String originFileName = file.getOriginalFilename();
        String ext = extractExt(originFileName);
        String uuid = UUID.randomUUID().toString();

        String saveFileName = time + "_" + uuid + "." + ext;

        Path path = Paths.get(fileBaseDir + File.separator + StringUtils.cleanPath(saveFileName));
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e) {
            return 0;
        }

        FileUploadVO vo = new FileUploadVO();

        vo.setOriginFileName(originFileName);
        vo.setFileName(saveFileName);
        vo.setExt(ext);
        vo.setPath(fileBaseDir + saveFileName);
        vo.setSize(Long.toString(file.getSize()));

        return dao.upload(vo);

    }

    private String extractExt(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }

    @Override
    public HashMap<String, Object> getLectureList(String keyword) {
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<FileUploadVO> list = new ArrayList<>();

        list = dao.getLectureList(keyword);

        map.put("item", list);

        return map;
    }
}

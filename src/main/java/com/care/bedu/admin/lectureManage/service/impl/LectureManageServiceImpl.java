package com.care.bedu.admin.lectureManage.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.bedu.admin.lectureManage.dao.LectureManageDao;
import com.care.bedu.admin.lectureManage.service.LectureManageService;
import com.care.bedu.lecture.vo.LectureVO;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class LectureManageServiceImpl implements LectureManageService{

    private String baseUrl = "C:/Desktop/";
    private String httpUrl = "http://172.30.1.85:8081/";

    @Autowired
    LectureManageDao manageDao;

    @Override
    public HashMap<String, Object> imgUpload(MultipartHttpServletRequest req, HttpServletResponse res) throws IOException {

        String managePath = "ADMIN/MANAGE/IMAGE/";

        HashMap<String, Object> map = new HashMap<>();

        MultipartFile multiFile = req.getFile("upload");

        String extention = multiFile.getOriginalFilename().split("\\.")[1];
        
        String uid = UUID.randomUUID().toString() + "." + extention;
        String uploadUrl = baseUrl+ managePath + uid;
        
        File file = new File(uploadUrl);
        
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        InputStream input = multiFile.getInputStream();
        OutputStream output = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        
        while(input.read(bytes) != -1){
            output.write(bytes);
        }

        input.close();
        output.flush();
        output.close();

        map.put("url", httpUrl+ managePath +uid);


        return map;
    }

    @Override
    public int lectInsert(MultipartHttpServletRequest req, HttpServletResponse res)
            throws IOException {
            String managePath = "ADMIN/MANAGE/THUMB/";

            MultipartFile thumbnail = req.getFile("thumbnail");

            LectureVO lectureVO = new LectureVO();

            String title = req.getParameter("title");
            String teacher = req.getParameter("teacher");
            String price = req.getParameter("price");
            int period = Integer.parseInt(req.getParameter("period")) ;
            String summary = req.getParameter("summary");
            String contents = req.getParameter("contents");
            String cateCode = req.getParameter("cateCode");
            int regNum = Integer.parseInt(req.getParameter("regNum"));

            lectureVO.setTitle(title); lectureVO.setTeacher(teacher);
            lectureVO.setPrice(price); lectureVO.setLectPeriod(period);
            lectureVO.setLectSum(summary); lectureVO.setLectDesc(contents);
            lectureVO.setCategory(cateCode); lectureVO.setRegNum(regNum);

            String extention = thumbnail.getOriginalFilename().split("\\.")[1];
            
            String uid = UUID.randomUUID().toString() + "." + extention;

            String uploadUrl = baseUrl + managePath + uid;

            File file = new File(uploadUrl);

            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            InputStream input = thumbnail.getInputStream();
            OutputStream output = new FileOutputStream(file);
            byte[] bytes = new byte[1024];

            while(input.read(bytes) != -1){
                output.write(bytes);
            }

            input.close();
            output.flush();
            output.close();

            String thumbnailPath = httpUrl + managePath + uid;

            lectureVO.setThumbnail(thumbnailPath);

            // System.out.println(lectureVO);

            int result = 1;

            result = manageDao.lectInsert(lectureVO);

            return result;
    }
    
}

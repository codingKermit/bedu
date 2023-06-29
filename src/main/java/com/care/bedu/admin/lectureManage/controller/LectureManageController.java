package com.care.bedu.admin.lectureManage.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.bedu.admin.lectureManage.service.impl.LectureManageServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/admin/lectManage")
public class LectureManageController {


    @Autowired
    LectureManageServiceImpl lectManageService;

    @RequestMapping("/lectInsert")
    public ResponseEntity<String> lectInsert(){

        return ResponseEntity.ok().build();
    }

    @RequestMapping("/ImageUpload")
    public ResponseEntity<Object> imgUpload(MultipartHttpServletRequest req, HttpServletResponse res) throws IOException{
        HashMap<String, Object> map = new HashMap<>();
        map = lectManageService.imgUpload(req, res);

        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    
}

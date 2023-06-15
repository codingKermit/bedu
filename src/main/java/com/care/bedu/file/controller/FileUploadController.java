package com.care.bedu.file.controller;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.care.bedu.file.service.FileUploadService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
@Slf4j
@RequestMapping(value = "/api/file")
public class FileUploadController {
    
    private String uploadPath = "";


    @RequestMapping("/test")
    public String test(){
        return "this is test";
    }

    @Autowired
    private FileUploadService service;

    @GetMapping(value = "/upload.do")
    public ModelAndView upload(jakarta.servlet.http.HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("/file/fileUpload");
        return mv;
    }

    @PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
        Boolean result = false;
        result=service.upload(uploadFile, model);
    }

    @RequestMapping("/getLectureList")
    public HashMap<String, Object> getLectureList(String keyword){
        HashMap<String, Object> map = new HashMap<>();

        map = service.getLectureList(keyword);

        return map;
    }
}

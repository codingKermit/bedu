package com.care.bedu.file.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.care.bedu.file.service.FileUploadService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value = "/api/file")
public class FileUploadController {

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

    @PostMapping(value = "/uploadProcess.do", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public int uploadProcess(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        return service.upload(file);
    }

    @RequestMapping("/getLectureList")
    public HashMap<String, Object> getLectureList(String keyword){
        HashMap<String, Object> map = new HashMap<>();

        map = service.getLectureList(keyword);

        return map;
    }
}

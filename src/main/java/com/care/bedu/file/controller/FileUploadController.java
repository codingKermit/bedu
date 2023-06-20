package com.care.bedu.file.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.care.bedu.file.service.FileUploadService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	public ResponseEntity<String> uploadFormPost(MultipartHttpServletRequest request, Model model) throws IOException{
        MultipartFile file = request.getFile("videoFile");

        int chunkNumber = Integer.parseInt(request.getParameter("chunkNumber")) ;
        int totalChunk = Integer.parseInt(request.getParameter("totalChunk"));

        if(chunkNumber == totalChunk){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
        }
    }  

    @RequestMapping("/getLectureList")
    public HashMap<String, Object> getLectureList(String keyword){
        HashMap<String, Object> map = new HashMap<>();

        map = service.getLectureList(keyword);

        return map;
    }

    @RequestMapping("/getTotalLecture")
    public ResponseEntity<Object> getTotalLecture(){
        HashMap<String, Object> map = new HashMap<>();

        map = service.getTotalLecture();

        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
}

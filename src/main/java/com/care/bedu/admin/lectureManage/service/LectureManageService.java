package com.care.bedu.admin.lectureManage.service;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface LectureManageService {
    
    public HashMap<String, Object> imgUpload(MultipartHttpServletRequest req, HttpServletResponse res)  throws IOException;
}

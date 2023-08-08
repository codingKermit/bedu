package com.care.bedu.bookmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.bookmark.service.impl.BookmarkServiceImpl;

@RestController
@RequestMapping("/api/bookmark")
public class BookmarkController {
    
    @Autowired
    BookmarkServiceImpl service;

    /** 북마크 저장 & 삭제 */
    @RequestMapping("/inOut")
    public ResponseEntity inOut(int lectNum, String userName){

        return new ResponseEntity(service.inOut(lectNum, userName), HttpStatus.OK);
    }

    /** 북마크 조회 */
    @RequestMapping("/getList")
    public ResponseEntity getList(String userName){

        return new ResponseEntity<>(service.getList(userName),HttpStatus.OK);
    }

}

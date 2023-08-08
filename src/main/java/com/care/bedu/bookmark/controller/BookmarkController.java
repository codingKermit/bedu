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

    /** 북마크 저장 & 삭제 
     *  저장 - 1 반환
     *  삭제 - 2 반환
    */
    @RequestMapping("/inOut")
    public ResponseEntity inOut(int lectNum, String userName){

        return new ResponseEntity(service.inOut(lectNum, userName), HttpStatus.OK);
    }

    /** 북마크 목록 조회 */
    @RequestMapping("/getList")
    public ResponseEntity getList(String userName){

        return new ResponseEntity<>(service.getList(userName),HttpStatus.OK);
    }

    // 북마크 여부 조회
    @RequestMapping("/getBookmark")
    public ResponseEntity getBookmark(String userName, int lectNum){

        return new ResponseEntity(service.getBookmark(lectNum, userName), HttpStatus.OK);
    }

}

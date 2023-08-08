package com.care.bedu.bookmark.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.bookmark.dao.BookmarkDAO;
import com.care.bedu.bookmark.service.BookmarkService;
import com.care.bedu.bookmark.vo.BookmarkVO;

@Service
public class BookmarkServiceImpl implements BookmarkService{

    @Autowired
    BookmarkDAO dao;

    @Override
    public int inOut(int lectNum, String userName) {

        return dao.inOut(lectNum, userName);
    }

    @Override
    public List<BookmarkVO> getList(String userName) {

        return dao.getList(userName);
    }
    
}

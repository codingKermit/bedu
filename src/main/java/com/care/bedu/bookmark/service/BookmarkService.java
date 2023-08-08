package com.care.bedu.bookmark.service;

import java.util.List;

import com.care.bedu.bookmark.vo.BookmarkVO;

public interface BookmarkService {
    
    public int inOut(int lectNum, String userName);                      //북마크 저장 & 삭제 프로시저 호출
    public List<BookmarkVO> getList(String userName);      //북마크 조회
}

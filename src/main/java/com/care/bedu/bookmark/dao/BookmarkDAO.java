package com.care.bedu.bookmark.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.bookmark.vo.BookmarkVO;

@Mapper
public interface BookmarkDAO {
    
    public int inOut(int lectNum, String userName);     // 북마크 저장 & 삭제 프로시저 호출
    public List<BookmarkVO> getList(String userName);   // 북마크 목록 조회
    
}

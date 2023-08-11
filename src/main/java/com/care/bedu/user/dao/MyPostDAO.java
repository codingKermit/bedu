package com.care.bedu.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.bedu.community.freeBoard.vo.FreeVO;
import com.care.bedu.community.qna.vo.QnaVO;

@Mapper
public interface MyPostDAO {
    
    public List<QnaVO> getQna(String userName, @RequestParam("order") String order);
    public List<FreeVO> getFree(String userName, @RequestParam("order") String order);

}

package com.care.bedu.community.ans.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.ans.vo.AnsVO;

@Mapper
public interface AnsDAO {
	//글조회
	public ArrayList<AnsVO> viewList(AnsVO ansVO);
	
	//글 작성
	public int viewWrite(AnsVO ansVO);
	
	//글 총개수
	public int ansTotal(int qnanum);
	
	//글 삭제
	public int ansDelete(Integer ansBdNum)throws SQLException;
}

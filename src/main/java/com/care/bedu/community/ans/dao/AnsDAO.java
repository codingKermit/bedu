package com.care.bedu.community.ans.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.ans.vo.AnsVO;

@Mapper
public interface AnsDAO {
	public ArrayList<AnsVO> viewList(AnsVO ansVO);
	public int viewWrite(AnsVO ansVO);
	public int ansTotal(int qnanum);
	public int ansDelete(Integer ansBdNum)throws SQLException;
}

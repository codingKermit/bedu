package com.care.bedu.community.freeBoard.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.freeBoard.vo.FreeVO;

@Mapper
public interface FreeDAO {
	
	public ArrayList<FreeVO> viewlist(FreeVO freeVO);
	public int viewWrite(FreeVO freeVO);
	public FreeVO viewone(int num);
	public int viewdelete(int num);
	public int viewupdate(FreeVO freeVO);
	public int getTotal();
	public ArrayList<FreeVO> viewsearch(FreeVO freeVO);
	public int cntUp(int num);
	public int likeUp(int num);
	public int getlike(int num);
	
}

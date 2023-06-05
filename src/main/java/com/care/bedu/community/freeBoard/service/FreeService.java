package com.care.bedu.community.freeBoard.service;

import java.util.ArrayList;

import com.care.bedu.community.freeBoard.vo.FreeVO;

public interface FreeService {
	
	public ArrayList<FreeVO> listProc(FreeVO freeVO);
	public int boardwrite(FreeVO freeVO);
	public FreeVO viewone(int num);
	public int viewdelete(int num);
	public int viewupdate(FreeVO freeVO);
	public int getTotal();
	public int likeUp(int num);
}

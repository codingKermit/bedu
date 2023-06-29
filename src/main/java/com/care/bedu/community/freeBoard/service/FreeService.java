package com.care.bedu.community.freeBoard.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.care.bedu.community.freeBoard.vo.FreeVO;

public interface FreeService {
	
	public ArrayList<FreeVO> listProc(FreeVO freeVO); 	//게시글 조회
	public int boardwrite(FreeVO freeVO);				//게시글 등록
	public FreeVO viewone(int num);						//게시글 상세보기
	public int viewdelete(int num);						//게시글 삭제
	public int viewupdate(int comm_num, String title, String content);				//게시글 수정
	public int getTotal();								//게시글 전체 개수	
	public ArrayList<FreeVO> getUserId(String userName);
	public HashMap<String, Object> likeUp(int num, String email, String regId) throws Exception;
	public int likeDown(int num, String userName, int likeBdNum);//게시글 좋아요 증가
}

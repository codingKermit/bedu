package com.care.bedu.community.ans.service;

import java.util.ArrayList;

import com.care.bedu.community.ans.vo.AnsVO;

public interface AnsService {
	
	//게시글 조회
	public ArrayList<AnsVO> getlist(AnsVO ansVO); 	
	
	//게시글 등록
	public int boardwrite(AnsVO ansVO);	
	
	//글 총개수
	public int ansTotal(int qnanum);
	
	//글 삭제
	public int ansDelete(int ansBdNum) throws Exception;
}

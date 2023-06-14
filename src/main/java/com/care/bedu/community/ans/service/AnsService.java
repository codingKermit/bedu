package com.care.bedu.community.ans.service;

import java.util.ArrayList;

import com.care.bedu.community.ans.vo.AnsVO;

public interface AnsService {
	
	public ArrayList<AnsVO> getlist(AnsVO ansVO); 	//게시글 조회
	public int boardwrite(AnsVO ansVO);				//게시글 등록
}

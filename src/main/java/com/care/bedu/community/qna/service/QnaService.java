package com.care.bedu.community.qna.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.care.bedu.community.qna.vo.QnaVO;

public interface QnaService {
	
	public ArrayList<QnaVO> listProc(QnaVO qnaVO);			//게시글 리스트 5개 조회 
	public int boardwrite(QnaVO qnaVO);						//게시글 작성
	public QnaVO viewone(int num);							//게시글 상세보기
	public int viewdelete(int num);							//게시글 삭제
	public int viewupdate(QnaVO qnaVO);						//게시글 수정
	public int getTotal();//게시글 전체 개수
	public ArrayList<QnaVO> getUserId(String userName);
	public HashMap<String, Object> likeUp(int num, String email, String regId) throws Exception;
	public int likeDown(int num, String userName, int likeBdNum);//게시글 좋아요 증가
}

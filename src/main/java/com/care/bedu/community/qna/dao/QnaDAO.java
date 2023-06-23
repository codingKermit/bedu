package com.care.bedu.community.qna.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.qna.vo.QnaVO;

@Mapper
public interface QnaDAO {
	
	public ArrayList<QnaVO> viewlist(QnaVO qnaVO);				//게시글 전체 조회
	public int viewWrite(QnaVO qnaVO);							//게시글 작성
	public QnaVO viewone(int num);								//게시글 상세보기
	public int viewupdate(QnaVO qnaVO);					//게시글 수정
	public int viewdelete(int qnanum);								//게시글 삭제
	public ArrayList<QnaVO> viewsearch(QnaVO qnaVO);			//게시글 키워드 검색
	public int getTotal();										//게시글 전체 개수 조회
	public ArrayList<QnaVO> getuserId(String userName);
	public void qnaCntUp(int num);
	public ArrayList<QnaVO> getuserName(String userId);
	public int likeUp(int num, String userName);										//게시글 좋아요 1증가
}

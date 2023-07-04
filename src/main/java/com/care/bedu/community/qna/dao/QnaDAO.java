package com.care.bedu.community.qna.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.qna.vo.QnaVO;

@Mapper
public interface QnaDAO {
	
	public List<QnaVO> viewlist(QnaVO qnaVO) throws SQLException;				//게시글 전체 조회
	public int viewWrite(QnaVO qnaVO);							//게시글 작성
	public QnaVO viewone(int num);								//게시글 상세보기
	public int viewupdate(QnaVO qnaVO);					//게시글 수정
	public int viewdelete(int qnanum);								//게시글 삭제
	public ArrayList<QnaVO> viewsearch(QnaVO qnaVO);			//게시글 키워드 검색
	public int getTotal();										//게시글 전체 개수 조회
	public ArrayList<QnaVO> getuserId(String userName);
	public void qnaCntUp(int num);
	public List<QnaVO> getuserName(String userId) throws SQLException;
	public int likeUp(int num);										//게시글 좋아요 1증가
	public int likeDown(int num);
	public int likeName(int qsnum, String userid);
}

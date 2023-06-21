package com.care.bedu.community.freeBoard.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.freeBoard.vo.FreeVO;

@Mapper
public interface FreeDAO {
	
	public ArrayList<FreeVO> viewlist(FreeVO freeVO);			//게시글 기본 조회
	public int viewWrite(FreeVO freeVO);						//게시글 작성
	public FreeVO viewone(int num);								//게시글 상세 보기
	public int viewdelete(int num);								//게시글 삭제
	public int viewupdate(FreeVO freeVO);						//게시글 수정
	public int getTotal();										//게시글 전체 개수
	public ArrayList<FreeVO> viewsearch(FreeVO freeVO);			//게시글 키워드 검색 기능		
	public int cntUp(int num);						//조회수 1증가
	public int likeUp(int num);						//좋아요 1증가					
	public ArrayList<FreeVO> getuserId(String userName);
}

package com.care.bedu.community.freeBoard.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.freeBoard.service.FreeService;
import com.care.bedu.community.freeBoard.vo.FreeVO;

@RestController
@RequestMapping("/api")
public class FreeController {
	
	@Autowired
	private FreeService freeService;
	
	@RequestMapping(value="/freBd/boardList", method= {RequestMethod.GET, RequestMethod.POST})   		//게시글 조회
	public ArrayList<FreeVO> freeList(FreeVO freeVO){
		ArrayList<FreeVO> list = freeService.listProc(freeVO);
		return list;
		
	}
	
	@RequestMapping(value="/freBd/write", method=RequestMethod.POST)				//게시글 작성
	public int freeWrite(FreeVO freeVO){
		int result = freeService.boardwrite(freeVO);
		return result == 1 ? 1: 0;
	}
	
	@RequestMapping(value="/freBd/detail", method=RequestMethod.GET)				//상세보기
	public FreeVO freeDetail(int num){
		return freeService.viewone(num);	
	}
	
	@RequestMapping(value="/freBd/edit", method=RequestMethod.POST)					//게시글 수정
	public int freeEdit(int comm_num, String title, String content){
		int result = freeService.viewupdate(comm_num, title, content);
		return result == 1? 1:0;	
	}
	
	@RequestMapping(value="/freBd/delete", method=RequestMethod.GET)				//게시글 삭제
	public int freeDelete(int num){
		int result = freeService.viewdelete(num);
		return result == 1? 1:0;
	}
	
	@RequestMapping("/freBd/total")													//전체 게시글 개수 조회
	public int getTotal(){
		return freeService.getTotal();
	}
	
	@RequestMapping(value="/free/getUserId", method = RequestMethod.GET)													//게시글 전체 개수 조회
	 public ArrayList<FreeVO> getUserId(String userName){
	 	 return freeService.getUserId(userName);
	 }
	
	@RequestMapping(value="/free/likeUp", method = RequestMethod.GET)				//게시글 좋아요 개수 1증가
	 public HashMap<String, Object> freelikeUp(int num, String userName, String regId) throws Exception{
		 return freeService.likeUp(num, userName, regId);
	 }
	 
	 @RequestMapping(value="/free/likeDown", method = RequestMethod.GET)													//게시글 전체 개수 조회
	 public int freelikeDown(int num, String userName, int likebdnum){
		 return freeService.likeDown(num, userName, likebdnum);
	 }
	
}

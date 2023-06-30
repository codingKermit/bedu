package com.care.bedu.community.qna.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.qna.service.QnaService;
import com.care.bedu.community.qna.vo.QnaVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class QnaController {
	
	 @Autowired
	 private QnaService qnaService;
	
	 @RequestMapping(value="/qna/qnaList", method= {RequestMethod.GET, RequestMethod.POST})    //게시글 조회
	 public ArrayList<QnaVO> qnaList(QnaVO qnaVO) throws Exception{
		 return qnaService.listProc(qnaVO);
	 }
	
	 @RequestMapping(value="/qna/qnaWrite", method=RequestMethod.POST)				//게시글 작성
	 public int qnaWrite(QnaVO qnaVO){
		 return qnaService.boardwrite(qnaVO);
	 }
	
	 @RequestMapping(value="/qna/qnaDetail", method=RequestMethod.GET)				//게시글 상세보기
	 public QnaVO qnaDetail(int num){
	 	return qnaService.viewone(num);
	 }
	
	 @RequestMapping(value="/qna/qnaEdit", method=RequestMethod.POST)				//게시글 수정
	 public int qnaEdit(String title, String content, int qna_bd_num){
		QnaVO qnaVO = new QnaVO();
		qnaVO.setContent(content);
		qnaVO.setQna_bd_num(qna_bd_num);
		qnaVO.setTitle(title);
	 	int result = qnaService.viewupdate(qnaVO);
	 	return result> 0? 1:0;	
	 }
	
	 @RequestMapping(value="/qna/qnaDelete", method=RequestMethod.GET)				//게시글 삭제
	 public int qnaDelete(int num){
	 	return qnaService.viewdelete(num);
	 }

	 @RequestMapping("/qna/total")													//게시글 전체 개수 조회
	 public int getTotal(){
	 	return qnaService.getTotal();
	 }
	 
	 @RequestMapping(value="/qna/getUserId", method = RequestMethod.GET)		//현제 로그인된 닉네임에 해당하는 userid조회											//게시글 전체 개수 조회
	 public ArrayList<QnaVO> getUserName(String userName){
		 return qnaService.getUserId(userName);
	 }
	
	 @RequestMapping(value="/qna/likeUp", method = RequestMethod.GET)				//게시글 좋아요 개수 1증가
	 public HashMap<String, Object> likeUp(int num, String userName, String regId) throws Exception{
		 return qnaService.likeUp(num, userName, regId);
	 }
	 
	 @RequestMapping(value="/qna/likeDown", method = RequestMethod.GET)													//게시글 전체 개수 조회
	 public int likeDown(int num, String userName, int likebdnum){
		 return qnaService.likeDown(num, userName, likebdnum);
	 }

}

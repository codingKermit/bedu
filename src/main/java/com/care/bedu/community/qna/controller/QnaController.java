package com.care.bedu.community.qna.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.qna.service.QnaService;
import com.care.bedu.community.qna.vo.QnaVO;
import com.care.bedu.user.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class QnaController {
	
	 @Autowired
	 private QnaService qnaService;
	 @Autowired
	 private JwtUtil jwtUtil;
	
	 @RequestMapping(value="/qna/qnaList", method= {RequestMethod.GET, RequestMethod.POST})    //게시글 조회
	 public ArrayList<QnaVO> qnaList(QnaVO qnaVO){
		 return qnaService.listProc(qnaVO);
	 }
	
	 @RequestMapping(value="/qna/qnaWrite", method=RequestMethod.POST)				//게시글 작성
	 public int qnaWrite(QnaVO qnaVO){
		 System.out.println("값:"+qnaVO.getUser_name());
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
	 public int qnaDelete(String num){
	 	int strnum = Integer.parseInt(num);
	 	return qnaService.viewdelete(strnum);
	 }

	 @RequestMapping("/qna/total")													//게시글 전체 개수 조회
	 public int getTotal(){
	 	return qnaService.getTotal();
	 }
	
	 @RequestMapping(value="/qna/likeUp", method = RequestMethod.GET)				//게시글 좋아요 개수 1증가
	 public HashMap<String, Object> likeUp(int num, String email){
		 System.out.println("값:"+ email);
		 HashMap<String, Object> map = new HashMap<>();
		 
		 int result = qnaService.likeUp(num, email) == 1? num : 0;
		 
		 map.put("email", email);
		 map.put("nums", result);
	 	 return map;
	 }

}

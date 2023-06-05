package com.care.bedu.community.qna.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.qna.service.QnaService;
import com.care.bedu.community.qna.vo.QnaVO;

@RestController
@RequestMapping("/api")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value="/qna/qnaList", method= {RequestMethod.GET, RequestMethod.POST})    //게시글 조회
	public ArrayList<QnaVO> qnaList(QnaVO qnaDTO){
		return qnaService.listProc(qnaDTO);
	}
	
	@RequestMapping(value="/qna/qnaWrite", method=RequestMethod.POST)				//게시글 작성
	public String qnaWrite(QnaVO qnaDTO){
		int result = qnaService.boardwrite(qnaDTO);
		return result > 0 ? "Y": "N";
	}
	
	@RequestMapping(value="/qna/qnaDetail", method=RequestMethod.GET)				//게시글 상세보기
	public QnaVO qnaDetail(int num){
		return qnaService.viewone(num);
	}
	
	@RequestMapping(value="/qna/qnaEdit", method=RequestMethod.POST)				//게시글 수정
	public String qnaEdit(QnaVO qnaDTO){
		int result = qnaService.viewupdate(qnaDTO);
		return result> 0? "Y":"N";	
	}
	
	@RequestMapping(value="/qna/qnaDelete", method=RequestMethod.GET)				//게시글 삭제
	public String qnaDelete(String num){
		int strnum = Integer.parseInt(num);
		int result = qnaService.viewdelete(strnum);
		return result> 0? "Y":"N";
	}

	@RequestMapping("/qna/total")													//게시글 전체 개수 조회
	public int getTotal(){
		return qnaService.getTotal();
	}
	
	@RequestMapping(value="/qna/likeUp", method = RequestMethod.GET)				//게시글 좋아요 개수 1증가
	public int likeUp(int num){
		return qnaService.likeUp(num);
		
	}

}

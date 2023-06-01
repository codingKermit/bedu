package com.care.bedu.community.qna.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.qna.dto.QnaDTO;
import com.care.bedu.community.qna.service.QnaService;

@RestController
@RequestMapping("/api")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value="/qna/qnaList", method= {RequestMethod.GET, RequestMethod.POST})
	public ArrayList<QnaDTO> qnaList(QnaDTO qnaDTO){
		ArrayList<QnaDTO> list = qnaService.listProc(qnaDTO);
		return list;
	}
	
	@RequestMapping(value="/qna/qnaWrite", method=RequestMethod.POST)
	public String qnaWrite(QnaDTO qnaDTO){
		int result = qnaService.boardwrite(qnaDTO);
		return result > 0 ? "Y": "N";
	}
	
	@RequestMapping(value="/qna/qnaDetail", method=RequestMethod.GET)
	public QnaDTO qnaDetail(int num){
		return qnaService.viewone(num);
	}
	
	@RequestMapping(value="/qna/qnaEdit", method=RequestMethod.POST)
	public String qnaEdit(QnaDTO qnaDTO){
		int result = qnaService.viewupdate(qnaDTO);
		return result> 0? "Y":"N";	
	}
	
	@RequestMapping(value="/qna/qnaDelete", method=RequestMethod.GET)
	public String qnaDelete(String num){
		int strnum = Integer.parseInt(num);
		int result = qnaService.viewdelete(strnum);
		return result> 0? "Y":"N";
	}

	@RequestMapping("/qna/total")
	public int getTotal(){
		return qnaService.getTotal();
	}
	
	@RequestMapping(value="/qna/likeUp", method = RequestMethod.GET)
	public int likeUp(int num){
		int result = qnaService.likeUp(num);
		return result == 1? 1:0;
	}

}

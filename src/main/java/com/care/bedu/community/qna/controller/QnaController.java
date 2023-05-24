package com.care.bedu.community.qna.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.dto.CommunityDTO;
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
		qnaDTO.setUser_id("honggildong"); //테스트를 위해 임시로 넣음 회원객체에서 userId가지고 와야함
		qnaDTO.setReg_id(qnaDTO.getUser_id());
		int result = qnaService.boardwrite(qnaDTO);
		return result > 0 ? "Y": "N";
	}
	
	@RequestMapping(value="/qna/qnaDetail", method=RequestMethod.GET)
	public QnaDTO qnaDetail(int num){
		System.out.println("여기서 받는 번호:"+ num);
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

}

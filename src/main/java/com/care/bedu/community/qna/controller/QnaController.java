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
	
	@RequestMapping(value="/community/qnaList", method= {RequestMethod.GET, RequestMethod.POST})
	public ArrayList<QnaDTO> qnaList(QnaDTO qnaDTO){
		ArrayList<QnaDTO> list = qnaService.listProc(qnaDTO);
		return list;
	}

}

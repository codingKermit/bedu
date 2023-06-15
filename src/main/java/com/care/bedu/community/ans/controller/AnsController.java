package com.care.bedu.community.ans.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.ans.service.AnsService;
import com.care.bedu.community.ans.vo.AnsVO;

@RestController
@RequestMapping("/api")
public class AnsController {
	
	@Autowired
	private AnsService ansService;
	
	//답글게시글 조회
	@RequestMapping(value="/ans/getans", method= {RequestMethod.GET, RequestMethod.POST})
	public ArrayList<AnsVO> getReply(AnsVO ansVO){
		ArrayList<AnsVO> list = ansService.getlist(ansVO);
		return list;
	}
	
	//답글게시글 작성
	@RequestMapping(value="/ans/write", method=RequestMethod.POST)
	public int ansWrite(AnsVO ansVO){
		return ansService.boardwrite(ansVO);
	}
}

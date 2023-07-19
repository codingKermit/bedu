package com.care.bedu.community.reply.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.reply.service.ReplyService;
import com.care.bedu.community.reply.vo.ReplyVO;

@RestController
@RequestMapping("/api")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	//게시글 조회
	@RequestMapping(value="/reply/getreply", method= {RequestMethod.GET, RequestMethod.POST})   		
	public ArrayList<ReplyVO> getReply(ReplyVO replyVO){
//		System.out.println("replycomnum"+ replyVO.getCommNum());
		return replyService.getreply(replyVO);
		
	}
	
	//게시글 작성
	@RequestMapping(value="/reply/write", method=RequestMethod.POST)				
	public int replyWrite(Integer commNum, String userName, String content, String regId, Integer qsNum, Integer ansNum){
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setCommNum(commNum);
		replyVO.setUserName(userName);
		replyVO.setContent(content);
		replyVO.setRegId(regId);
		replyVO.setQsNum(qsNum);
		replyVO.setAnsNum(ansNum);
		return replyService.boardwrite(replyVO);
	}
	
	//게시글 총 개수
	@RequestMapping(value="/reply/replyTotal", method=RequestMethod.GET)													//게시글 전체 개수 조회
	public int replyTotal(int num){
	 	return replyService.replyTotal(num);
	}
	
	//게시글 삭제
	@RequestMapping(value="/reply/replydelete", method=RequestMethod.GET)													//게시글 전체 개수 조회
	public int replyDelete(int rnum) {
		return replyService.replydelete(rnum);
		
	}
}

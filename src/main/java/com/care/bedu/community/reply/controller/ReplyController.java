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
	
	
	@RequestMapping(value="/reply/getreply", method= {RequestMethod.GET, RequestMethod.POST})   		//게시글 조회
	public ArrayList<ReplyVO> getReply(ReplyVO replyVO){
		return replyService.getreply(replyVO);
		
	}
	
	@RequestMapping(value="/reply/write", method=RequestMethod.POST)				//게시글 작성
	public int replyWrite(int commNum, String userName, String content, String regId){
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setCommNum(commNum);
		replyVO.setUserName(regId);
		replyVO.setContent(content);
		replyVO.setRegId(regId);
		return replyService.boardwrite(replyVO);
	}
	
	@RequestMapping(value="/reply/replyTotal", method=RequestMethod.GET)													//게시글 전체 개수 조회
	public int replyTotal(int num){
	 	return replyService.replyTotal(num);
	}
	
	@RequestMapping(value="/reply/replydelete", method=RequestMethod.GET)													//게시글 전체 개수 조회
	public int replyDelete(int rnum) throws Exception{
		return replyService.replydelete(rnum);
		
	}
}

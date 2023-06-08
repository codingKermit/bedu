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
		return null;
	}
	
	@RequestMapping(value="/reply/write", method=RequestMethod.POST)				//게시글 작성
	public int replyWrite(ReplyVO replyVO){
		return replyService.boardwrite(replyVO);
	}
}

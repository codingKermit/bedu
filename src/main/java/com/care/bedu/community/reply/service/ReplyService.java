package com.care.bedu.community.reply.service;

import java.util.ArrayList;

import com.care.bedu.community.reply.vo.ReplyVO;

public interface ReplyService {
	
	public ArrayList<ReplyVO> getreply(ReplyVO replyVO); 	//게시글 조회
	public int boardwrite(ReplyVO replyVO);				//게시글 등록
}

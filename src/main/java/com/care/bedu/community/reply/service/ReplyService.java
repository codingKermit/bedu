package com.care.bedu.community.reply.service;

import java.util.ArrayList;

import com.care.bedu.community.reply.vo.ReplyVO;

public interface ReplyService {
	
	//게시글 조회
	public ArrayList<ReplyVO> getreply(ReplyVO replyVO);
	//게시글 등록
	public int boardwrite(ReplyVO replyVO);
	//댓글 총개수
	public int replyTotal(int num);
	//댓글 삭제
	public int replydelete(int replynum);
	
	public int replyupdate(ReplyVO replyVO);
}

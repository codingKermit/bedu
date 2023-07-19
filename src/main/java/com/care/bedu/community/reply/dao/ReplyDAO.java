package com.care.bedu.community.reply.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.reply.vo.ReplyVO;

@Mapper
public interface ReplyDAO {
	//자유댓글조회
	public ArrayList<ReplyVO> viewList(ReplyVO replyVO);
	//답변에 대한 댓글조회
	public ArrayList<ReplyVO> viewList3(ReplyVO replyVO);
	//댓글쓰기
	public int viewWrite(ReplyVO replyVO);
	//질문의 답변의 댓글쓰기
	public int viewWrite2(ReplyVO replyVO);
	//댓글개수
	public int replyTotal(int num);
	//댓글삭제
	public int replyDelete(int replynum);							
}

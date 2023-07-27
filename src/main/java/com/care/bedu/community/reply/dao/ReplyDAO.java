package com.care.bedu.community.reply.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.reply.vo.ReplyVO;

@Mapper
public interface ReplyDAO {
	//댓글조회
	public ArrayList<ReplyVO> viewList(ReplyVO replyVO);
	//댓글쓰기
	public int viewWrite(ReplyVO replyVO);
	//댓글개수
	public int replyTotal(int num);
	//댓글삭제
	public int replyDelete(int replynum);	
	
	//1대1 문의사항 답급
	public int inquiryInsert(ReplyVO replyVO);
	
	//1대1 문의사항 답글 조회
	public ArrayList<ReplyVO> inquiryList(ReplyVO replyVO);
	
//	//1대1 문의사항 답글 삭제
	public int inquiryDelete(int inquiryNum);
}

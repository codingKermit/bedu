package com.care.bedu.community.reply.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.reply.vo.ReplyVO;

@Mapper
public interface ReplyDAO {
	public ArrayList<ReplyVO> viewList(ReplyVO replyVO);
	public int viewWrite(ReplyVO replyVO);
	public int replyTotal(int num);
}

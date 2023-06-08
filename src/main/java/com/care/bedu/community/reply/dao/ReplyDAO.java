package com.care.bedu.community.reply.dao;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.community.reply.vo.ReplyVO;

@Mapper
public interface ReplyDAO {
	public int viewWrite(ReplyVO replyVO);
}

package com.care.bedu.community.reply.service.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.reply.dao.ReplyDAO;
import com.care.bedu.community.reply.service.ReplyService;
import com.care.bedu.community.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired private ReplyDAO replyDAO;
	
	@Override
	public ArrayList<ReplyVO> getreply(ReplyVO replyVO) {
		return null;
	}

	@Override
	public int boardwrite(ReplyVO replyVO) {
		replyVO.setLecturenum(437);	//강의 정보 글번호
		replyVO.setRwNum(151);	//강의 후기 테이블 번호
		replyVO.setCommNum(44);	//자유게시판 글번호
		replyVO.setUserId("test@test.com");
		replyVO.setRegId(replyVO.getUserId());
		return replyDAO.viewWrite(replyVO);
	}

}

package com.care.bedu.community.reply.service.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.reply.dao.ReplyDAO;
import com.care.bedu.community.reply.service.ReplyService;
import com.care.bedu.community.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired private ReplyDAO replyDAO;
	
	//댓글조회
	@Override
	public ArrayList<ReplyVO> getreply(ReplyVO replyVO) {
		ArrayList<ReplyVO> list =replyDAO.viewList(replyVO);
		for(ReplyVO reply: list) {
			reply.setReplyDate(reply.getRegDate());
		}
		return list;
	}

	//댓글쓰기
	@Override
	public int boardwrite(ReplyVO replyVO) {
		return replyDAO.viewWrite(replyVO);
	}

	//댓글 총개수
	@Override
	public int replyTotal(int num) {
		// TODO Auto-generated method stub
		return replyDAO.replyTotal(num);
	}

	//댓글 삭제
	@Override
	public int replydelete(int replynum) {
		return replyDAO.replyDelete(replynum);
	}

}

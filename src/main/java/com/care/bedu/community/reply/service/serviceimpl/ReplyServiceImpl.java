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
	
	//댓글조회
	@Override
	public ArrayList<ReplyVO> getreply(ReplyVO replyVO) {
		
		if(replyVO.getQsNum() != null && replyVO.getQsNum() > 0 && replyVO.getAnsNum() == null || replyVO.getAnsNum()==0) {
			return replyDAO.viewList3(replyVO);
		}else if(replyVO.getQsNum() != null && replyVO.getQsNum() > 0) {
			return replyDAO.viewList2(replyVO);
		}
		return replyDAO.viewList(replyVO);
	}

	//댓글쓰기
	@Override
	public int boardwrite(ReplyVO replyVO) {
		if(replyVO.getQsNum() != null && replyVO.getQsNum() > 0 && replyVO.getAnsNum()!= null && replyVO.getAnsNum()>0) {
			
			return replyDAO.viewWrite2(replyVO);
		}
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

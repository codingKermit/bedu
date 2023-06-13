package com.care.bedu.community.reply.service.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.reply.dao.ReplyDAO;
import com.care.bedu.community.reply.service.ReplyService;
import com.care.bedu.community.reply.vo.ReplyVO;
import com.care.bedu.user.dao.MemberDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired private ReplyDAO replyDAO;
	@Autowired private MemberDAO memberDAO;
	
	@Override
	public ArrayList<ReplyVO> getreply(ReplyVO replyVO) {
		return replyDAO.viewList(replyVO);
	}

	@Override
	public int boardwrite(ReplyVO replyVO) {
		replyVO.setLecturenum(null);	//강의 정보 글번호
		replyVO.setRwNum(null);	//강의 후기 테이블 번호
		replyVO.setFaqNum(null);//Qna 글번호
//		replyVO.setCommNum(44);	//자유게시판 글번호
		replyVO.setUserId("tet@tet.tet");
		replyVO.setRegId(replyVO.getUserId());
		return replyDAO.viewWrite(replyVO);
	}

}

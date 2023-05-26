package com.care.bedu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.care.bedu.community.dao.CommunityDAO;
import com.care.bedu.community.dto.CommunityDTO;
import com.care.bedu.community.qna.dao.QnaDAO;
import com.care.bedu.community.qna.dto.QnaDTO;
import com.care.bedu.community.serviceimpl.CommunityServiceImpl;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommunityData {
	
	@Autowired
	CommunityDAO communitydao;
	
	@Autowired
	QnaDAO qnadao;
	
	@DisplayName("1. 커뮤니티 데이터 생성")
	@Test
	public void test() {
		for(int i=0; i<20; i++) {
			CommunityDTO communitydto = new CommunityDTO();
			communitydto.setTitle("질문제목");
			communitydto.setContent("질문내용들");
			communitydto.setUser_id("user12");
			communitydto.setComm_like_yn(0);
			communitydto.setComm_cnt(0);
			communitydto.setReg_id(communitydto.getUser_id());
			communitydao.viewwrite(communitydto);
		}
	}
	
	@DisplayName("2. 질문답변 데이터 생성")
	@Test
	public void qnatest() {
		for(int i=0; i<20; i++) {
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setTitle("질문제목");
			qnaDTO.setContent("질문내용들");
			qnaDTO.setUser_id("user88");
			qnaDTO.setQna_like_yn(0);
			qnaDTO.setQna_cnt(0);
			qnaDTO.setReg_id(qnaDTO.getUser_id());
			qnadao.viewWrite(qnaDTO);
		}
	}

}

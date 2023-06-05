package com.care.bedu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.care.bedu.community.freeBoard.dao.FreeDAO;
import com.care.bedu.community.freeBoard.vo.FreeVO;
import com.care.bedu.community.qna.dao.QnaDAO;
import com.care.bedu.community.qna.vo.QnaVO;

@SpringBootTest
public class TestConn {
	
	@Autowired
	private FreeDAO community;
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Test
    public void Connection() {
		for(int i=0; i<20; i++) {
			FreeVO dto = new FreeVO();
			dto.setUser_id("user25");
			dto.setReg_id(dto.getUser_id());
			dto.setComm_like_yn(0);
			dto.setComm_cnt(0);
			dto.setTitle("질문");
			dto.setContent("내용");
			community.viewWrite(dto);
		}
	}
	
	@Test
    public void qnaConnection() {
		for(int i=0; i<20; i++) {
			QnaVO dto = new QnaVO();
			dto.setUser_id("user99");
			dto.setReg_id(dto.getUser_id());
			dto.setQna_like_yn(0);
			dto.setQna_cnt(0);
			dto.setTitle("질문");
			dto.setContent("내용");
			qnaDAO.viewWrite(dto);
		}
	}

}

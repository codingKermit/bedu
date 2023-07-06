package com.care.bedu.community.ans.service.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.ans.dao.AnsDAO;
import com.care.bedu.community.ans.service.AnsService;
import com.care.bedu.community.ans.vo.AnsVO;

@Service
public class AnsServiceImpl implements AnsService{
	
	@Autowired private AnsDAO ansDAO;
	
	//글조회
	@Override
	public ArrayList<AnsVO> getlist(AnsVO ansVO) {
		ArrayList<AnsVO> list = ansDAO.viewList(ansVO);
		for(AnsVO ans : list) {
			ans.setStrAnsDate(regdates(ans.getAnsDate()));
		}
		return list;
	}
	
	//날짜 변환
	private String regdates(Date regdate) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String strRegdate = simple.format(regdate);
		return strRegdate;
	}

	//글쓰기
	@Override
	public int boardwrite(AnsVO ansVO) {
		ansVO.setAnsLikeCnt(0);
		return ansDAO.viewWrite(ansVO);
	}

	//글총개수
	@Override
	public int ansTotal(int qnanum) {
		return ansDAO.ansTotal(qnanum);
	}

	//글삭제
	@Override
	public int ansDelete(int ansBdNum) throws Exception {
		
		return ansDAO.ansDelete(ansBdNum);
	}

}

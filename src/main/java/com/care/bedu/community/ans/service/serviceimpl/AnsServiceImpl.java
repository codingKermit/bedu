package com.care.bedu.community.ans.service.serviceimpl;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.ans.dao.AnsDAO;
import com.care.bedu.community.ans.service.AnsService;
import com.care.bedu.community.ans.vo.AnsVO;

@Service
public class AnsServiceImpl implements AnsService{
	
	@Autowired private AnsDAO ansDAO;
	
	//답변조회
	@Override
	public ArrayList<AnsVO> getlist(AnsVO ansVO) {
		
		ArrayList<AnsVO> list = ansDAO.viewList(ansVO);
		for(AnsVO ans: list) {
			ans.setAnsDate(ans.getRegDate());
		}
		
		return list;
		
	}

	//답변쓰기
	@Override
	public int boardwrite(AnsVO ansVO) {
		ansVO.setAnsLikeCnt(0);
		return ansDAO.viewWrite(ansVO);
	}

	//답변총개수
	@Override
	public int ansTotal(int qnanum) {
		return ansDAO.ansTotal(qnanum);
	}

	//글삭제
	@Override
	public int ansDelete(int ansBdNum) {
		
		return ansDAO.ansDelete(ansBdNum);
	}

}

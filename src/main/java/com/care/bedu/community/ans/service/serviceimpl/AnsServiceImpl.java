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
		return ansDAO.viewList(ansVO);
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
	public int ansDelete(AnsVO ansVO) {
		if(ansVO.getQsBdNum() > 0 && ansVO.getUserName().equals("ADMIN") && ansVO.getUserName() != null) {
			return ansDAO.ansalladminDelete(ansVO);
		}else if(ansVO.getAnsBdNum() != null && ansVO.getAnsBdNum() >0) {
			return ansDAO.ansDelete(ansVO.getAnsBdNum());
		}else {
			return 0;
		}
		
	}

	@Override
	public int boardedit(AnsVO ansVO) {
		return ansDAO.ansedit(ansVO);
	}

}

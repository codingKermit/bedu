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
//	@Autowired private MemberDAO memberDAO;
	
	@Override
	public ArrayList<AnsVO> getlist(AnsVO ansVO) {
		return ansDAO.viewList(ansVO);
	}

	@Override
	public int boardwrite(AnsVO ansVO) {
		ansVO.setUserId("tet@tet.tet");
		ansVO.setRegId(ansVO.getUserId());
		return ansDAO.viewWrite(ansVO);
	}

}

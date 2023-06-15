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
//	@Autowired private MemberDAO memberDAO;
	
	@Override
	public ArrayList<AnsVO> getlist(AnsVO ansVO) {
		ArrayList<AnsVO> list = ansDAO.viewList(ansVO);
		for(AnsVO ans : list) {
			ans.setStrAnsDate(regdates(ans.getAnsDate()));
		}
		return list;
	}
	
	private String regdates(Date regdate) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String strRegdate = simple.format(regdate);
		return strRegdate;
	}

	@Override
	public int boardwrite(AnsVO ansVO) {
		ansVO.setRegId(ansVO.getUserId());
		return ansDAO.viewWrite(ansVO);
	}

}
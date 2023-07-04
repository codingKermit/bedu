package com.care.bedu.inquiry.service.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.inquiry.dao.InquiryDAO;
import com.care.bedu.inquiry.service.InquiryService;
import com.care.bedu.inquiry.vo.InquiryVO;

@Service
public class InquiryServiceImpl implements InquiryService {

	@Autowired private InquiryDAO inquiryDAO;
	
	@Override
	public ArrayList<InquiryVO> inquirylist(InquiryVO inquiryVO) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public int getTotal() {
		return inquiryDAO.getTotal();
	}
	
	@Override
	public int boardwrite(InquiryVO inquiryVO) {
		
		inquiryVO.setRegId(inquiryVO.getUserName());
		System.out.println(inquiryVO);
		return inquiryDAO.inquiryWrite(inquiryVO);
	}
	
	@Override
	public ArrayList<InquiryVO> getUserName(String userName){
		return inquiryDAO.getuserName(userName);
	}
	
	@Override
	public InquiryVO inquiryone(int num) {
		InquiryVO inquiryVO = inquiryDAO.inquiryone(num);
				return inquiryVO;
	}

}

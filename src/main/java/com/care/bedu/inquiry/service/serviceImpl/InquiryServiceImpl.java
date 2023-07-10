package com.care.bedu.inquiry.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.inquiry.dao.InquiryDAO;
import com.care.bedu.inquiry.service.InquiryService;
import com.care.bedu.inquiry.vo.InquiryVO;

@Service
public class InquiryServiceImpl implements InquiryService {

	@Autowired
	private InquiryDAO inquiryDAO;

	@Override
	public List<InquiryVO> list() throws Exception {
		List<InquiryVO> inquirylist = inquiryDAO.inquirylist();
		return inquirylist;
	}

	@Override
	public int getTotal() {
		return inquiryDAO.getTotal();
	}

	@Override
	public int inquiryWriteSave(InquiryVO inquiryVO) {
		inquiryVO.setRegId(inquiryVO.getUserName());
		if (inquiryVO.getFileName() == null || inquiryVO.getFileName() == "") {
			inquiryVO.setFileName("");
		}
		if (inquiryVO.getFileType() == null || inquiryVO.getFileType() == "") {
			inquiryVO.setFileType("");
		}

		return inquiryDAO.inquiryWriteSave(inquiryVO);
	}

	@Override
	public ArrayList<InquiryVO> getUserName(String userName) {
		return inquiryDAO.getuserName(userName);
	}

	@Override 
	public InquiryVO inquiryone(int num) {
		InquiryVO inquiryVO = inquiryDAO.inquiryone(num);
		
		System.out.println(inquiryone(num));

		return inquiryVO;
	}

}

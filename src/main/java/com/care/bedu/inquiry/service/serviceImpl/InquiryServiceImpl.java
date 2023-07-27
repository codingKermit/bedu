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

	//게시글 리스트 조회
	@Override
	public List<InquiryVO> list() throws Exception {
	    List<InquiryVO> inquirylist = inquiryDAO.inquirylist();
	    for (InquiryVO inquiry : inquirylist) {
	        int replyCnt = inquiryDAO.getReplyCnt(inquiry.getVocNum());
	        inquiry.setReplyCnt(replyCnt);
	    }
	    return inquirylist;
	}
	
	//게시글 전체 갯수 조회
	@Override
	public int getTotal() {
		return inquiryDAO.getTotal();
	}

	//게시글 등록
	@Override
	public int inquiryWriteSave(InquiryVO inquiryVO) {
		if (inquiryVO.getFileName() == null || inquiryVO.getFileName() == "") {
			inquiryVO.setFileName("");
		}
		if (inquiryVO.getFileType() == null || inquiryVO.getFileType() == "") {
			inquiryVO.setFileType("");
		}

		return inquiryDAO.inquiryWriteSave(inquiryVO);
	}

	//유저아이디조회
	@Override
	public ArrayList<InquiryVO> getUserId(String userName) {
		return inquiryDAO.getuserId(userName);
	}

	//글 조회 
	@Override 
	public InquiryVO inquiryone(Integer vocNum)   {
		InquiryVO inquiryVO = new InquiryVO();
		inquiryVO = inquiryDAO.inquiryone(vocNum);
		int replyCnt = inquiryDAO.getReplyCnt(vocNum);
		inquiryVO.setReplyCnt(replyCnt);
		return inquiryVO;
	}
	
	@Override
	public InquiryVO inquirypassword(Integer password) {
		InquiryVO inquiryVO = inquiryDAO.inquirypassword(password);
		return inquiryVO;
	}

}

package com.care.bedu.inquiry.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.inquiry.dao.InquiryDAO;
import com.care.bedu.inquiry.service.InquiryService;
import com.care.bedu.inquiry.vo.InquiryVO;

@Service
public class InquiryServiceImpl implements InquiryService {

	private InquiryDAO inquiryDAO;

	@Autowired
	public InquiryServiceImpl(InquiryDAO inquiryDAO) {
		this.inquiryDAO = inquiryDAO;

	}

	@Override
	public List<HashMap<String, Object>> getAllInquiry(int page, int size) {
		int startIndex = (page - 1) * size;
		int endIndex = startIndex + size;
		List<HashMap<String, Object>> allReviews = inquiryDAO.getAllInquiry();
		if (startIndex > Math.min(endIndex, allReviews.size())) {
			return new ArrayList<>(); // 시작 인덱스가 문의 목록의 범위를 벗어나면 빈 리스트를 반환합니다.
		}

		return allReviews.subList(startIndex, Math.min(endIndex, allReviews.size()));
	}

	@Override
	public InquiryVO createInquiry(InquiryVO inquiryVO) {
		// 문의를 생성하는 로직입니다.
		inquiryDAO.createInquiry(inquiryVO);
		return inquiryVO;
	}

	@Override
	public List<HashMap<String, Object>> getSearchedInquiry(String keyword) {
		// 검색 키워드에 기반하여 문의 필터링
		List<HashMap<String, Object>> allInquiry = inquiryDAO.getAllInquiry();
		List<HashMap<String, Object>> searchedInquiry = new ArrayList<>();
		for (HashMap<String, Object> inquiry : allInquiry) {
			String title = (String) inquiry.get("TITLE");
			String content = (String) inquiry.get("CONTENT");
			if (title.toLowerCase().contains(keyword) || content.toLowerCase().contains(keyword)) {
				searchedInquiry.add(inquiry);
			}
		}
		return searchedInquiry;
	}
}

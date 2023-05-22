package com.care.bedu.community.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.dao.CommunityDAO;
import com.care.bedu.community.dto.CommunityDTO;
import com.care.bedu.community.service.CommunityService;

import lombok.RequiredArgsConstructor;

@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	private CommunityDAO communityMapper;

//	@Override
//	public ArrayList<CommunityDTO> listProc() {
//		HashMap<String, Object> map = new HashMap<>();
//		return communityMapper.vielist();
//	}

	@Override
	public int boardwriter(CommunityDTO communityDTO) {
		return communityMapper.viewwriter(communityDTO);
	}

	@Override
	public CommunityDTO viewone(int num) {
		return communityMapper.viewone(num);
	}

	@Override
	public int viewdelete(int num) {
		return communityMapper.viewdelete(num);
	}

	@Override
	public int viewupdate(CommunityDTO communityDTO) {
		return communityMapper.viewupdate(communityDTO);
	}

	@Override
	public ArrayList<CommunityDTO> listProc(CommunityDTO communityDTO) {
		communityDTO.setPage((communityDTO.getPage()-1)*3);			//시작할 현재 페이지 로직
		if(communityDTO.getKeyword() != null) {				//검색 키워드 조건 (키워드 없으면 기본 조회)
			return communityMapper.viewsearch(communityDTO);
		}
		return communityMapper.viewlist(communityDTO);
	}

	@Override
	public int getTotal() {										//최종 끝 페이지
		return communityMapper.getTotal();
	}
	
	

}

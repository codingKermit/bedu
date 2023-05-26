package com.care.bedu.community.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.community.dao.CommunityDAO;
import com.care.bedu.community.dto.CommunityDTO;
import com.care.bedu.community.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	private CommunityDAO communityMapper;

	@Override
	public int boardwrite(CommunityDTO communityDTO) {
		communityDTO.setUser_id("user12"); //테스트를 위해 임시로 넣음 회원객체에서 userId가지고 와야함
		communityDTO.setReg_id(communityDTO.getUser_id());
		communityDTO.setComm_cnt(0);
		communityDTO.setComm_like_yn(0);
		return communityMapper.viewwrite(communityDTO);
	}

	@Override
	public CommunityDTO viewone(int num) {
		int result = communityMapper.cntUp(num);
		CommunityDTO communityDTO =communityMapper.viewone(num);
		return communityDTO;
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
		communityDTO.setPage((communityDTO.getPage()-1)*10);			//시작할 현재 페이지 로직
		if(communityDTO.getKeyword() != null) {				//검색 키워드 조건 (키워드 없으면 기본 조회)
			return communityMapper.viewsearch(communityDTO);
		}
		return communityMapper.viewlist(communityDTO);
		
	}

	@Override
	public int getTotal() {										//최종 끝 페이지
		return communityMapper.getTotal();
	}

	@Override
	public int likeUp(int num) {
		return communityMapper.likeUp(num);
	}
	
	

}

package com.care.bedu.community.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;

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

	@Override
	public ArrayList<CommunityDTO> listProc() {
		HashMap<String, Object> map = new HashMap<>();
		return communityMapper.vielist();
	}

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
	
	

}

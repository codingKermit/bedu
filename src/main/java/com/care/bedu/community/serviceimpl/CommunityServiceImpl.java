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
		return communityMapper.viewwrite(communityDTO);
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
		communityDTO.setPage((communityDTO.getPage()-1)*10);			//시작할 현재 페이지 로직
		if(communityDTO.getKeyword() != null) {				//검색 키워드 조건 (키워드 없으면 기본 조회)
			return communityMapper.viewsearch(communityDTO);
		}
		ArrayList<CommunityDTO> list = communityMapper.viewlist(communityDTO);
//		System.out.println("전체 글번호 개수:"+ communityMapper.getTotal());
//		int total = communityMapper.getTotal();
//		for(int i=0; i<list.size(); i++) {
//			CommunityDTO communitylist = list.get(i);
//			for(int viewpage=total; viewpage>0; viewpage--) {
//				communitylist.setViewNumber(viewpage);
//				break;
//			}
//		}
		return list;
	}

	@Override
	public int getTotal() {										//최종 끝 페이지
		return communityMapper.getTotal();
	}
	
	

}

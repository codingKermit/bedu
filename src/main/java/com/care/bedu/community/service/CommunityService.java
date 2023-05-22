package com.care.bedu.community.service;

import java.util.ArrayList;

import com.care.bedu.community.dto.CommunityDTO;

public interface CommunityService {
	
//	public ArrayList<CommunityDTO> listProc();
	public ArrayList<CommunityDTO> listProc(CommunityDTO communityDTO);
	public int boardwriter(CommunityDTO communityDTO);
	public CommunityDTO viewone(int num);
	public int viewdelete(int num);
	public int viewupdate(CommunityDTO communityDTO);
	public int getTotal();
}

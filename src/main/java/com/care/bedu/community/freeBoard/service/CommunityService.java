package com.care.bedu.community.freeBoard.service;

import java.util.ArrayList;

import com.care.bedu.community.freeBoard.dto.CommunityDTO;

public interface CommunityService {
	
	public ArrayList<CommunityDTO> listProc(CommunityDTO communityDTO);
	public int boardwrite(CommunityDTO communityDTO);
	public CommunityDTO viewone(int num);
	public int viewdelete(int num);
	public int viewupdate(CommunityDTO communityDTO);
	public int getTotal();
	public int likeUp(int num);
}

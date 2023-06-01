package com.care.bedu.community.freeBoard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.freeBoard.dto.CommunityDTO;
import com.care.bedu.community.freeBoard.service.CommunityService;

@RestController
@RequestMapping("/api")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	
	@RequestMapping(value="/community/boardList", method= {RequestMethod.GET, RequestMethod.POST})
	public ArrayList<CommunityDTO> communityList(CommunityDTO communityDTO){
		return communityService.listProc(communityDTO);
	}
	
	@RequestMapping(value="/community/write", method=RequestMethod.POST)
	public int communityWrite(CommunityDTO communityDTO){
		
		int result = communityService.boardwrite(communityDTO);
		return result == 1 ? 1: 0;
	}
	
	@RequestMapping(value="/community/detail", method=RequestMethod.GET)
	public CommunityDTO communityDetail(int num){
		return communityService.viewone(num);	
	}
	
	@RequestMapping(value="/community/edit", method=RequestMethod.POST)
	public int communityEdit(CommunityDTO communityDTO){
		int result = communityService.viewupdate(communityDTO);
		return result == 1? 1:0;	
	}
	
	@RequestMapping(value="/community/delete", method=RequestMethod.GET)
	public int communityDelete(int num){
		int result = communityService.viewdelete(num);
		return result == 1? 1:0;
	}
	
	@RequestMapping("/community/total")
	public int getTotal(){
		return communityService.getTotal();
	}
	
	@RequestMapping(value="/community/likeUp", method = RequestMethod.GET)
	public int likeUp(int num){
		int result = communityService.likeUp(num);
		return result == 1? 1:0;
	}
	
	
}

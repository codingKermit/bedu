package com.care.bedu.community.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.dto.CommunityDTO;
import com.care.bedu.community.service.CommunityService;

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
	public String communityWrite(CommunityDTO communityDTO){
		int result = communityService.boardwrite(communityDTO);
		return result > 0 ? "Y": "N";
	}
	
	@RequestMapping(value="/community/detail", method=RequestMethod.GET)
	public CommunityDTO communityDetail(int num){
		return communityService.viewone(num);	
	}
	
	@RequestMapping(value="/community/edit", method=RequestMethod.POST)
	public String communityEdit(CommunityDTO communityDTO){
		int result = communityService.viewupdate(communityDTO);
		return result> 0? "Y":"N";	
	}
	
	@RequestMapping(value="/community/delete", method=RequestMethod.GET)
	public String communityDelete(String num){
		int strnum = Integer.parseInt(num);
		int result = communityService.viewdelete(strnum);
		return result> 0? "Y":"N";
	}
	
	@RequestMapping("/community/total")
	public int getTotal(){
		return communityService.getTotal();
	}
	
}

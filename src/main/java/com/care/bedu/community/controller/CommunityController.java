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
		System.out.println("키워드:"+ communityDTO.getKeyword());
		ArrayList<CommunityDTO> list = communityService.listProc(communityDTO);
		System.out.println("리스트:"+ list);
		return list;
	}
	
	@RequestMapping(value="/community/writer", method=RequestMethod.POST)
	public String communityWriter(CommunityDTO communityDTO){
		communityDTO.setUserId("admin"); //테스트를 위해 임시로 넣음 회원객체에서 userId가지고 와야함
		int result = communityService.boardwriter(communityDTO);
		return result > 0 ? "Y": "N";
	}
	
	@RequestMapping(value="/community/detail", method=RequestMethod.GET)
	public CommunityDTO communityDetail(int num){
//		System.out.println("detail번호:"+num);
		return communityService.viewone(num);	
	}
	
	@RequestMapping(value="/community/edit", method=RequestMethod.POST)
	public String communityEdit(CommunityDTO communityDTO){
		System.out.println("edit번호:"+communityDTO.toString());
		System.out.println(communityDTO.toString());
		int result = communityService.viewupdate(communityDTO);
		return result> 0? "Y":"N";	
	}
	
	@RequestMapping(value="/community/delete", method=RequestMethod.GET)
	public String communityDelete(String num){
		System.out.println("delete번호:"+ num);
		int strnum = Integer.parseInt(num);
		int result = communityService.viewdelete(strnum);
		return result> 0? "Y":"N";
	}
	
}

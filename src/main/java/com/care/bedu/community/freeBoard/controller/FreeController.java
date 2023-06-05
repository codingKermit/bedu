package com.care.bedu.community.freeBoard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.community.freeBoard.service.FreeService;
import com.care.bedu.community.freeBoard.vo.FreeVO;

@RestController
@RequestMapping("/api")
public class FreeController {
	
	@Autowired
	private FreeService freeService;
	
	@RequestMapping(value="/community/boardList", method= {RequestMethod.GET, RequestMethod.POST})
	public ArrayList<FreeVO> freeList(FreeVO freeVO){
		return freeService.listProc(freeVO);
		
	}
	
	@RequestMapping(value="/community/write", method=RequestMethod.POST)
	public int freeWrite(FreeVO freeVO){
		int result = freeService.boardwrite(freeVO);
		return result == 1 ? 1: 0;
	}
	
	@RequestMapping(value="/community/detail", method=RequestMethod.GET)
	public FreeVO freeDetail(int num){
		return freeService.viewone(num);	
	}
	
	@RequestMapping(value="/community/edit", method=RequestMethod.POST)
	public int freeEdit(FreeVO freeVO){
		int result = freeService.viewupdate(freeVO);
		return result == 1? 1:0;	
	}
	
	@RequestMapping(value="/community/delete", method=RequestMethod.GET)
	public int freeDelete(int num){
		int result = freeService.viewdelete(num);
		return result == 1? 1:0;
	}
	
	@RequestMapping("/community/total")
	public int getTotal(){
		return freeService.getTotal();
	}
	
	@RequestMapping(value="/community/likeUp", method = RequestMethod.GET)
	public int likeUp(int num){
		return freeService.likeUp(num);
	}
	
	
}

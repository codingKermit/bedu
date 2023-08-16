/* 작업자 우지원 */



package com.care.bedu.membership.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.membership.service.MembershipService;

@RestController
@RequestMapping("/api/membership")
public class MembershipController {
	
	@Autowired
	MembershipService service;
	
	/* 구독자 저장 */
	@RequestMapping("/getSubscribe")
	public ResponseEntity<Object> getSubscribe(String nickname,String type){
		
		if(service.getSubscribe(nickname,type) == 1) {
			return ResponseEntity.ok().build();
		} else{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	/* 구독 정보 조회 */
	@RequestMapping("/getSubInfo")
	public ResponseEntity<Object> getSubInfo(String nickname){
		
		HashMap<String, Object> map = new HashMap<>();
		
		map = service.getSubInfo(nickname);
		
		return new ResponseEntity<Object>(map,HttpStatus.OK);
	}
	
	
}

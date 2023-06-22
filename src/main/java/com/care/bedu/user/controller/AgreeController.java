package com.care.bedu.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.bedu.user.service.AgreeService;
import com.care.bedu.user.vo.AgreeVO;

@RestController
@RequestMapping("/api")
public class AgreeController {
	private final AgreeService agreeService;
	
	public AgreeController(AgreeService agreeService) {
        this.agreeService = agreeService;
    }

    @GetMapping("/agree/{id}")
    public List<AgreeVO> getAgreementById(@PathVariable Long id) {
        return agreeService.getAgreementById(id);
    }
}

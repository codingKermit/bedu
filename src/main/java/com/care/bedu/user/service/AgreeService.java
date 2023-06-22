package com.care.bedu.user.service;

import java.util.List;

import com.care.bedu.user.vo.AgreeVO;

public interface AgreeService {

	List<AgreeVO> getAgreementById(Long id);
}

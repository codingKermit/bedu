package com.care.bedu.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.care.bedu.user.vo.AgreeVO;

@Mapper
public interface AgreeDAO {

	List<AgreeVO> getAllAgreements();
	List<AgreeVO> getAgreementById(Long id);
}

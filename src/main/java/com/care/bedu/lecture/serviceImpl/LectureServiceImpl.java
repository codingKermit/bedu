package com.care.bedu.lecture.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.lecture.dao.LectureDao;
import com.care.bedu.lecture.dto.LectureDetailDto;
import com.care.bedu.lecture.dto.LectureDto;
import com.care.bedu.lecture.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService{

	@Autowired
	private LectureDao dao;
	
	@Override
	public ArrayList<LectureDto> getLectureList(String category, int page) {
		// TODO Auto-generated method stub
		ArrayList<LectureDto> list = new ArrayList<>();
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("begin", (page-1)*20+1);
		
		list = dao.getLectureList(map);
		
		return list;
	}

	@Override
	public LectureDto getLectureDetail(int num) {
		// TODO Auto-generated method stub
		LectureDto dto = new LectureDto();
		dto = dao.getLectureDetail(num);
		
		return dto;
	}

	@Override
	public HashMap<String, ArrayList<LectureDto>> getLectureField() {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<LectureDto>> map = new HashMap<>();
		ArrayList<LectureDto> dto = new ArrayList<>();


		
		dto = dao.getLectureField("base");map.put("base", dto);
		dto = dao.getLectureField("data"); map.put("data", dto);
		dto = dao.getLectureField("web"); map.put("web", dto);
		dto = dao.getLectureField("lang"); map.put("lang", dto);
		dto = dao.getLectureField("ai"); map.put("ai", dto);
		dto = dao.getLectureField("programming"); map.put("programming", dto);
		dto = dao.getLectureField("tools"); map.put("tools", dto);
		dto = dao.getLectureField("major"); map.put("major", dto);
		dto = dao.getLectureField("design"); map.put("design", dto);
		
		
		return map;
	}
	
	@Override
	public ArrayList<LectureDetailDto> getVideoList(int num) {
		// TODO Auto-generated method stub
		ArrayList<LectureDetailDto> list = new ArrayList<>();
		
		list = dao.getVideoList(num);
		
		return list;
	}
	
}

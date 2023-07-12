package com.care.bedu.lecture.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.lecture.dao.LectureFildDAO;
import com.care.bedu.lecture.service.LectureFildService;
import com.care.bedu.lecture.vo.LectureFildVO;

@Service
public class LectureFildServiceImpl implements LectureFildService{

    @Autowired
    LectureFildDAO dao;

    @Override
    public HashMap<String, Object> getList() {
        HashMap<String, Object> map = new HashMap<>();
        
        ArrayList<LectureFildVO> list = new ArrayList<>();

        list = dao.getList();

        map.put("item", list);

        return map;
    }
    
}

/* 작업자 우지원 */



package com.care.bedu.user.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.user.dao.MyPostDAO;
import com.care.bedu.user.service.MyPostService;

@Service
public class MyPostServiceImpl implements MyPostService{

    @Autowired
    MyPostDAO dao;

    @Override
    public Map<String, Object> getList(String userName, String order, String group) {
        HashMap<String, Object> map = new HashMap<>();

        if(group.equals("qna")){
            map.put("list", dao.getQna(userName, order));
        } else if (group.equals("free")){
            map.put("list", dao.getFree(userName, order));
        }

        return map;
    }
    
}

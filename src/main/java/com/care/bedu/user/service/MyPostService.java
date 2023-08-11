package com.care.bedu.user.service;

import java.util.Map;


public interface MyPostService {

    /** 본인이 작성한 게시글 조회 */
    public Map<String,Object> getList(String userName, String order, String group);
}

package com.care.bedu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.care.bedu.community.dao.CommunityDAO;
import com.care.bedu.community.dto.CommunityDTO;
import com.care.bedu.community.serviceimpl.CommunityServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommunityData {
	
	@Autowired
	CommunityDAO communitydao;
	
	@DisplayName("1. 커뮤니티 데이터 생성")
	@Test
	public void test() {
		for(int i=0; i<15; i++) {
			CommunityDTO qnadto = new CommunityDTO();
			qnadto.setTitle("질문합니다.");
			qnadto.setContents("질문내용들");
			qnadto.setWriter("홍길동");
			qnadto.setUserId("admin");
			communitydao.viewwrite(qnadto);
		}
	}

}

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
	
	/* 카테고리별 강의를 들어갔을 때 동영상 조회 */
	@Override
	public ArrayList<LectureDto> getLectureList(String category, int page) {
		// TODO Auto-generated method stub
		ArrayList<LectureDto> list = new ArrayList<>();
		


		HashMap<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("begin", (page-1)*20+1);
		
		list = dao.getLectureList(map);

		// 좋아요 여부 체크 후 반환 과정
		list = likeCheck(list);
		
		return list;
	}

	/* 강의 상세 정보 조회 */
	@Override
	public LectureDto getLectureDetail(int num) {
		// TODO Auto-generated method stub
		LectureDto dto = new LectureDto();
		dto = dao.getLectureDetail(num);
		
		return dto;
	}

	/* 분야별 강의 화면에 보여줄 데이터 조회.
	 * 분야별로 평점이 높은것으로 4개씩 조회하여 map에 담아 리턴
	 */
	@Override
	public HashMap<String, ArrayList<LectureDto>> getLectureField() {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<LectureDto>> map = new HashMap<>();
		ArrayList<LectureDto> dto = new ArrayList<>();

		dto = dao.getLectureField("base");map.put("base", likeCheck(dto));
		dto = dao.getLectureField("data"); map.put("data", likeCheck(dto));
		dto = dao.getLectureField("web"); map.put("web", likeCheck(dto));
		dto = dao.getLectureField("lang"); map.put("lang", likeCheck(dto));
		dto = dao.getLectureField("ai"); map.put("ai", likeCheck(dto));
		dto = dao.getLectureField("programming"); map.put("programming", likeCheck(dto));
		dto = dao.getLectureField("tools"); map.put("tools", likeCheck(dto));
		dto = dao.getLectureField("major"); map.put("major", likeCheck(dto));
		dto = dao.getLectureField("design"); map.put("design", likeCheck(dto));
		
		
		return map;
	}
	
	/* 해당 강의에 포함되어있는 커리큘럼을 조회 */
	@Override
	public ArrayList<LectureDetailDto> getVideoList(int num) {
		// TODO Auto-generated method stub
		ArrayList<LectureDetailDto> list = new ArrayList<>();
		
		list = dao.getVideoList(num);
		
		return list;
	}

	/* 검색을 통한 결과 조회 */
	@Override
	public HashMap<String, ArrayList<LectureDto>> lectureSearch(String keyword, int page) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<LectureDto>> map = new HashMap<>();
		ArrayList<LectureDto> dto = new ArrayList<>();

		HashMap<String, Object> arg = new HashMap<>();
		arg.put("keyword", keyword); arg.put("begin", (page-1)*20+1);
		dto = dao.lectureSearch(arg);
		map.put("item", dto);


		return map;
	}

	/* 검색했을때 해당 키워드로 조회되는 총 강의 수를 확인 */
	@Override
	public int searchTotal(String keyword) {
		// TODO Auto-generated method stub
		int result = 0;
		result = dao.searchTotal(keyword);
		return result;
	}

	/* 내가 좋아요한 강의 목록 조회 */
	@Override
	public ArrayList<Integer> getLikeList(String userId) {
		// TODO Auto-generated method stub

		ArrayList<Integer> list = new ArrayList<>();

		list = dao.getLikeList(userId);

		return list;
	}


	/* 내가 좋아요한 목록을 조회하고 likeYn 컬럼에 값 넣기 위한 메서드 */
	public ArrayList<LectureDto> likeCheck(ArrayList<LectureDto> dto){
		ArrayList<Integer> likes = new ArrayList<>();
		likes = dao.getLikeList("123");
		for(int i = 0; i< dto.size();i++){
			if(likes.contains(dto.get(i).getNum())){
				dto.get(i).setLikeYn(1);
			} else {
				dto.get(i).setLikeYn(0);
			}
		}
		return dto;
	}
	
}

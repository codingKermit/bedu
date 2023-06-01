package com.care.bedu.lecture.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.bedu.lecture.dao.LectureCategoryDAO;
import com.care.bedu.lecture.dao.LectureDAO;
import com.care.bedu.lecture.service.LectureService;
import com.care.bedu.lecture.vo.LectureCategoriesVO;
import com.care.bedu.lecture.vo.LectureDetailVO;
import com.care.bedu.lecture.vo.LectureVO;


@Service
public class LectureServiceImpl implements LectureService{

	@Autowired
	private LectureDAO lectureDao;

	@Autowired
	private LectureCategoryDAO cateDao;

	
	/* 카테고리별 강의를 들어갔을 때 동영상 조회 */
	@Override
	public ArrayList<Object>  getLectureList(String category) {
		/* 중분류에 따른 소분류 조회 -> 소분류에 따른 강의 목록 조회
		 * -> 자료 구조 정리하여 반환
		 */
		ArrayList<LectureCategoriesVO> dtos = new ArrayList<>(); // 소분류 카테고리 조회
		dtos = cateDao.getBot(category);
		HashMap<String, Object> map = new HashMap<>(); // 파라미터 맵

		ArrayList<LectureVO> list = new ArrayList<>(); // 강의 목록

		ArrayList<Object> result = new ArrayList<>(); // 반환 값
		

		for(LectureCategoriesVO dto : dtos){
			HashMap<String,Object> lect = new HashMap<>(); // 
			map.put("category", dto.getCateCode());
			list = lectureDao.getLectureList(map); // 소분류에 따른 강의 목록
			lect.put("cate_code", dto.getCateCode()); // 소분류 코드
			lect.put("cate_kor", dto.getCateKor()); // 소분류 한글
			lect.put("item",list); // 소분류 강의 목록
			result.add(lect);
		}

		return result;
	}



	/* 강의 상세 정보 조회 */
	@Override
	public LectureVO getLectureDetail(int num) {
		LectureVO dto = new LectureVO();
		dto = lectureDao.getLectureDetail(num);
		
		return dto;
	}

	/* 분야별 강의 화면에 보여줄 데이터 조회.
	 * 분야별로 평점이 높은것으로 4개씩 조회하여 map에 담아 리턴
	 */
	@Override
	public HashMap<String, ArrayList<LectureVO>> getLectureField() {
		HashMap<String, ArrayList<LectureVO>> map = new HashMap<>();
		ArrayList<LectureVO> dto = new ArrayList<>();

		dto = lectureDao.getLectureField("base");map.put("base", likeCheck(dto));
		dto = lectureDao.getLectureField("data"); map.put("data", likeCheck(dto));
		dto = lectureDao.getLectureField("web"); map.put("web", likeCheck(dto));
		dto = lectureDao.getLectureField("lang"); map.put("lang", likeCheck(dto));
		dto = lectureDao.getLectureField("ai"); map.put("ai", likeCheck(dto));
		dto = lectureDao.getLectureField("programming"); map.put("programming", likeCheck(dto));
		dto = lectureDao.getLectureField("tools"); map.put("tools", likeCheck(dto));
		dto = lectureDao.getLectureField("major"); map.put("major", likeCheck(dto));
		dto = lectureDao.getLectureField("design"); map.put("design", likeCheck(dto));
		
		
		return map;
	}
	
	/* 해당 강의에 포함되어있는 커리큘럼을 조회 */
	@Override
	public ArrayList<LectureDetailVO> getVideoList(int num) {
		ArrayList<LectureDetailVO> list = new ArrayList<>();
		
		list = lectureDao.getVideoList(num);
		
		return list;
	}

	/* 검색을 통한 결과 조회 */
	@Override
	public HashMap<String, ArrayList<LectureVO>> lectureSearch(String keyword, int page) {
		HashMap<String, ArrayList<LectureVO>> map = new HashMap<>();
		ArrayList<LectureVO> dto = new ArrayList<>();

		HashMap<String, Object> arg = new HashMap<>();
		arg.put("keyword", keyword); 
		arg.put("begin", (page-1)*5);
		dto = lectureDao.lectureSearch(arg);
		map.put("item", dto);


		return map;
	}

	/* 검색했을때 해당 키워드로 조회되는 총 강의 수를 확인 */
	@Override
	public int searchTotal(String keyword) {
		int result = 0;
		result = lectureDao.searchTotal(keyword);
		return result;
	}

	/* 내가 좋아요한 강의 목록 조회 */
	@Override
	public ArrayList<Integer> getLikeList(String userId) {

		ArrayList<Integer> list = new ArrayList<>();

		list = lectureDao.getLikeList(userId);

		return list;
	}


	/* 내가 좋아요한 목록을 조회하고 likeYn 컬럼에 값 넣기 위한 메서드 */
	public ArrayList<LectureVO> likeCheck(ArrayList<LectureVO> dto){
		ArrayList<Integer> likes = new ArrayList<>();
		likes = lectureDao.getLikeList("123");
		for(int i = 0; i< dto.size();i++){
			if(likes.contains(dto.get(i).getLectNum())){
				dto.get(i).setLikeYn(1);
			} else {
				dto.get(i).setLikeYn(0);
			}
		}
		return dto;
	}



	/* 신규 오픈 강좌 4개 조회 */
	@Override
	public HashMap<String, Object> getNewestLecture() {
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<LectureVO> list = new ArrayList<>();

		list = lectureDao.getNewestLecture();

		map.put("item", list);


		return map;
	}
	
}

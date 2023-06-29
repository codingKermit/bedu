package com.care.bedu.community.freeBoard.dao;

import org.apache.ibatis.annotations.Mapper;
import com.care.bedu.community.qna.vo.LikeCntVO;
  
  @Mapper 
  public interface FreeLikeCntDAO {
  
	  public int getfreelikeName(int commnum, String userid);
	  public int freelikedel(int likenum, int commnum);
	  public Integer getlikeFreenum(int commNum, String userName) throws Exception;
	  public int likeCntFreeSave(LikeCntVO likeCntVO);
  
  }
 

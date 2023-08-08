package com.care.bedu.bookmark.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkVO {
    
    private int num;            //고유번호
    private String userName;    //사용자이름
    private int lectNum;        //강의번호
}

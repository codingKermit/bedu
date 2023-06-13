package com.care.bedu.file.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("fileVO")
public class FileUploadVO {

    private String fileSeq;
    private String originFileName;
    private String fileName;
    private String ext;
    private String path;
    private String size;
    private String regSeq;
    private String regDt;

}

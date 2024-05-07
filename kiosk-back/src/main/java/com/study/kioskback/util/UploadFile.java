package com.study.kioskback.util;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UploadFile {

    protected String uploadFileName;
    protected String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}

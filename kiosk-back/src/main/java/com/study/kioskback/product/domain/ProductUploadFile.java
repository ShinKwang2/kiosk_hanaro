package com.study.kioskback.product.domain;

import com.study.kioskback.util.UploadFile;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class ProductUploadFile {

    @Column(name = "product_img_original")
    private String uploadFileName;

    @Column(name = "product_img_url")
    private String storeFileName;

    @Column(name =  "product_list_img_url")
    private String imageUrlForList;

    @Builder
    public ProductUploadFile(String uploadFileName, String storeFileName, String imageUrlForList) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.imageUrlForList = imageUrlForList;
    }

    public static ProductUploadFile from(UploadFile uploadFile) {
        return ProductUploadFile.builder()
                .uploadFileName(uploadFile.getUploadFileName())
                .storeFileName(uploadFile.getStoreFileName())
                .imageUrlForList(uploadFile.getStoreFileName())
                .build();
    }
}

package com.study.kioskback.api.product.dto;

import com.study.kioskback.api.product.domain.Product;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductPreviewDto {

    private Integer id;
    private String koreanName;
    private String englishName;
    private String imageUrlForList;
    private Integer price;

    public static ProductPreviewDto of(Product product) {
        return ProductPreviewDto.builder()
                .id(product.getId())
                .koreanName(product.getKoreanName())
                .englishName(product.getEnglishName())
                .imageUrlForList(product.getProductUploadFile().getImageUrlForList())
                .price(product.getPrice())
                .build();
    }
}

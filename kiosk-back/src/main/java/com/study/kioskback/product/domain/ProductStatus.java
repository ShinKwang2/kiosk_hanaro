package com.study.kioskback.product.domain;

import com.study.kioskback.error.exception.product.NotFoundProductStatus;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductStatus {

    SELLABLE("sellable","판매중"), SOLD_OUT("sold_out","품절");

    private String key;
    private String koreanName;

    ProductStatus(String key, String koreanName) {
        this.key = key;
        this.koreanName = koreanName;
    }

    public static ProductStatus convertToStatus(String key) {
        return Arrays.stream(ProductStatus.values())
                .filter(status -> status.key.equals(key))
                .findFirst()
                .orElseThrow(NotFoundProductStatus::new);
    }
}

package com.study.kioskback.api.product.domain;

public enum ProductOptionType {

    SINGLE("단품"),
    WITH_COKE("음료세트"),
    SET("세트");

    private String koreanName;

    ProductOptionType(String koreanName) {
        this.koreanName = koreanName;
    }
}

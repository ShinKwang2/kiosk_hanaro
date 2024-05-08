package com.study.kioskback.api.product.domain;

import com.study.kioskback.error.exception.product.NotFoundProductType;

import java.util.Arrays;

public enum ProductType {
    HAMBURGER("hamburger"),
    MC_MORNING("mcmorning"),
    DRINK("drink"),
    DESSERT("dessert");

    private final String key;

    ProductType(String key) {
        this.key = key;
    }

    public static ProductType convertToType(String key) {
        return Arrays.stream(ProductType.values())
                .filter(productType -> productType.key.equals(key))
                .findFirst()
                .orElseThrow(NotFoundProductType::new);
    }
}

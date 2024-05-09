package com.study.kioskback.api.product.dto;

import com.study.kioskback.api.product.domain.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSearch {

    private static final int MAX_SIZE = 100;

    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

    private String type;

    public ProductSearch(Integer page, Integer size, String type) {
        this.page = page;
        this.size = size;
        this.type = type;
    }

    public long getOffset() {
        return (long) (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }

    public long getLimit() {
        return (long) size;
    }

    public ProductType getProductType() {
        if (type == null) {
            return null;
        }
        return ProductType.convertToType(type);
    }
}

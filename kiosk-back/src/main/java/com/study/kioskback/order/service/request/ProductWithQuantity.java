package com.study.kioskback.order.service.request;

import com.study.kioskback.product.domain.Product;
import lombok.Getter;

@Getter
public class ProductWithQuantity {

    private Product product;
    private Integer quantity;

    public ProductWithQuantity(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
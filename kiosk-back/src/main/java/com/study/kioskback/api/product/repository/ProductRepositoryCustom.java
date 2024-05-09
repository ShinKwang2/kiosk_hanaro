package com.study.kioskback.api.product.repository;

import com.study.kioskback.api.product.domain.Product;
import com.study.kioskback.api.product.dto.ProductSearch;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findAllBy(ProductSearch productSearch);

    List<Product> findAllByIdIn(List<Integer> productIds);

    List<Product> findRecommend(ProductSearch productSearch);
}

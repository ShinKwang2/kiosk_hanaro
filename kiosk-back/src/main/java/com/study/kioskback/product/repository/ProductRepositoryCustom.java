package com.study.kioskback.product.repository;

import com.study.kioskback.product.domain.Product;
import com.study.kioskback.product.dto.ProductSearch;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findAllBy(ProductSearch productSearch);
}

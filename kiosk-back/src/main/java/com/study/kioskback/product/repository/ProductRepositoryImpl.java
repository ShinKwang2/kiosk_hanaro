package com.study.kioskback.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.kioskback.product.domain.Product;
import com.study.kioskback.product.domain.ProductType;
import com.study.kioskback.product.dto.ProductSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.study.kioskback.product.domain.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllBy(ProductSearch productSearch) {
        return jpaQueryFactory.selectFrom(product)
                .where(typeEq(productSearch.getProductType()))
                .fetch();
    }

    private BooleanExpression typeEq(ProductType productType) {
        return productType != null ? product.type.eq(productType) : null;
    }
}

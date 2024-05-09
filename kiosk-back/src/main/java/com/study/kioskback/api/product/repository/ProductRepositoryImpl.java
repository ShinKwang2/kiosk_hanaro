package com.study.kioskback.api.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.kioskback.api.product.domain.Product;
import com.study.kioskback.api.product.domain.ProductType;
import com.study.kioskback.api.product.dto.ProductSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.study.kioskback.api.product.domain.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllBy(ProductSearch productSearch) {
        return jpaQueryFactory.selectFrom(product)
                .where(typeEq(productSearch.getProductType()))
                .fetch();
    }

    @Override
    public List<Product> findRecommend(ProductSearch productSearch) {
        return jpaQueryFactory.selectFrom(product)
                .orderBy(product.createdAt.desc())
                .limit(10L)
                .fetch();
    }

    @Override
    public List<Product> findAllByIdIn(List<Integer> productIds) {
        return jpaQueryFactory.selectFrom(product)
                .where(product.id.in(productIds))
                .fetch();
    }

    private BooleanExpression typeEq(ProductType productType) {
        return productType != null ? product.type.eq(productType) : null;
    }
}

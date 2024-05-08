package com.study.kioskback.api.product.repository;

import com.study.kioskback.api.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {
}

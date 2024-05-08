package com.study.kioskback.api.order.repository;

import com.study.kioskback.api.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

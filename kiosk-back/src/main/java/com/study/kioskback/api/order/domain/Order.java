package com.study.kioskback.api.order.domain;

import com.study.kioskback.api.order.service.request.ProductWithQuantity;
import com.study.kioskback.error.exception.order.AlreadyAccepted;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {

    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_created_date")
    private LocalDateTime createdAt;

    @Column(name = "order_updated_date")
    private LocalDateTime updatedAt;

    @Builder
    private Order(OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Order createOrder(List<ProductWithQuantity> productWithQuantities, LocalDateTime registeredDateTime) {
        Order order = Order.builder()
                .orderStatus(OrderStatus.INIT)
                .createdAt(registeredDateTime)
                .updatedAt(registeredDateTime)
                .build();
        productWithQuantities.stream()
                .map(OrderProduct::createOrderProduct)
                .forEach(orderProduct -> orderProduct.addOrder(order));
        return order;
    }

    //==연관관계 편의 메서드==//
//    public void addOrderItem(OrderProduct... orderProducts) {
//        Arrays.stream(orderProducts)
//                .forEach(orderProduct -> this.orderProducts.add(orderProduct));
//        Arrays.stream(orderProducts)
//                .forEach(orderProduct -> orderProduct.addOrder(this));
//    }

    //==비즈니스 로직==//
    public void cancel() {
        if (orderStatus == OrderStatus.COMPLETED) {
            throw new AlreadyAccepted();
        }
        orderStatus = OrderStatus.CANCELED;
    }

    //==조회 로직==//
    public Integer getTotalPrice() {
        return this.orderProducts.stream()
                .mapToInt(OrderProduct::getTotalPrice)
                .sum();
    }

}

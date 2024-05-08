package com.study.kioskback.api.order.domain;

import com.study.kioskback.api.order.service.request.ProductWithQuantity;
import com.study.kioskback.api.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_product")
@Entity
public class OrderProduct {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "order_product_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_product_price", nullable = false)
    private Integer orderPrice;

    @Column(name = "order_product_quantity", nullable = false)
    private Integer quantity;

    @Builder
    private OrderProduct(Product product, Integer orderPrice, Integer quantity) {
        this.product = product;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    //== 생성 메서드==//
    public static OrderProduct createOrderProduct(ProductWithQuantity productWithQuantity) {
        return OrderProduct.builder()
                .product(productWithQuantity.getProduct())
                .orderPrice(productWithQuantity.getProduct().getPrice())
                .quantity(productWithQuantity.getQuantity())
                .build();
    }

    //==연관관계 편의메서드==//
    public void addOrder(Order order) {
        this.order = order;
        order.getOrderProducts().add(this);
    }

    //== 비즈니스 로직==//
    public Integer getTotalPrice() {
        return getOrderPrice() * getQuantity();
    }
}

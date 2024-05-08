package com.study.kioskback.api.order.service.response;

import com.study.kioskback.api.order.domain.OrderProduct;
import com.study.kioskback.api.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class OrderResponse {

    private Integer id;
    private int totalPrice;
    private LocalDateTime registeredDateTime;
    private List<ProductResponse> products;

    @Builder
    private OrderResponse(Integer id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
    }
    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getUpdatedAt())
                .products(order.getOrderProducts().stream()
                        .map(ProductResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }

    @ToString
    @Getter
    public static class ProductResponse {

        private Integer id;
        private String productNameKr;
        private String productNameEn;
        private Integer orderPrice;
        private Integer quantity;

        @Builder
        private ProductResponse(Integer id, String productNameKr, String productNameEn, Integer orderPrice, Integer quantity) {
            this.id = id;
            this.productNameKr = productNameKr;
            this.productNameEn = productNameEn;
            this.orderPrice = orderPrice;
            this.quantity = quantity;
        }

        public static ProductResponse of(OrderProduct orderProduct) {
            return ProductResponse.builder()
                    .id(orderProduct.getProduct().getId())
                    .productNameKr(orderProduct.getProduct().getKoreanName())
                    .productNameEn(orderProduct.getProduct().getEnglishName())
                    .orderPrice(orderProduct.getOrderPrice())
                    .quantity(orderProduct.getQuantity())
                    .build();
        }
    }
}

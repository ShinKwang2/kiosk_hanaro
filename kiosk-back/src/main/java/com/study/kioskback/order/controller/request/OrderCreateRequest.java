package com.study.kioskback.order.controller.request;

import com.study.kioskback.order.service.request.OrderCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class OrderCreateRequest {

    private String phoneNumber;

    private List<OrderProductRequest> orderProducts;

    @Builder
    private OrderCreateRequest(String phoneNumber, List<OrderProductRequest> orderProducts) {
        this.phoneNumber = phoneNumber;
        this.orderProducts = orderProducts;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
                .productRequests(orderProducts)
                .build();
    }

    @NoArgsConstructor
    @Getter
    public static class OrderProductRequest {
        private Integer productId;
        private Integer quantity;

        @Builder
        private OrderProductRequest(Integer productId, Integer quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }
}

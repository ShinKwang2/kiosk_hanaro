package com.study.kioskback.api.order.service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.study.kioskback.api.order.controller.request.OrderCreateRequest.*;

@Getter
@NoArgsConstructor
public class OrderCreateServiceRequest {

    private List<OrderProductRequest> orderProducts;

    @Builder
    private OrderCreateServiceRequest(List<OrderProductRequest> productRequests) {
        this.orderProducts = productRequests;
    }
}

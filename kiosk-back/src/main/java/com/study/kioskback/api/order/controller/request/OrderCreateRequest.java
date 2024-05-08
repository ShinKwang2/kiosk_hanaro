package com.study.kioskback.api.order.controller.request;

import com.study.kioskback.api.order.service.request.OrderCreateServiceRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class OrderCreateRequest {

    private String phoneNumber;

    @NotEmpty(message = "주문 번호 리스트는 필수입니다.")
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

        @NotBlank(message = "상품 ID는 필수 입니다")
        private Integer productId;

        @Min(value = 1, message = "최소 수량은 1 이상입니다")
        private Integer quantity;

        @Builder
        private OrderProductRequest(Integer productId, Integer quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }
}

package com.study.kioskback.api.order.controller;

import com.study.kioskback.api.ApiResponse;
import com.study.kioskback.api.order.controller.request.OrderCreateRequest;
import com.study.kioskback.api.order.service.OrderService;
import com.study.kioskback.api.order.service.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@Validated @RequestBody OrderCreateRequest request) {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return ApiResponse.ok(orderService.createOrder(request.getPhoneNumber(), request.toServiceRequest(), registeredDateTime));
    }
}

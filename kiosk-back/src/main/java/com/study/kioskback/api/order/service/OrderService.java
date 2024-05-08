package com.study.kioskback.api.order.service;

import com.study.kioskback.api.order.controller.request.OrderCreateRequest;
import com.study.kioskback.api.order.service.request.OrderCreateServiceRequest;
import com.study.kioskback.api.order.service.request.ProductWithQuantity;
import com.study.kioskback.api.user.domain.User;
import com.study.kioskback.api.user.domain.UserConst;
import com.study.kioskback.api.user.domain.UserRepository;
import com.study.kioskback.api.order.domain.Order;
import com.study.kioskback.api.order.repository.OrderRepository;
import com.study.kioskback.api.order.service.response.OrderResponse;
import com.study.kioskback.api.product.domain.Product;
import com.study.kioskback.api.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(String phoneNumber, OrderCreateServiceRequest request, LocalDateTime registeredDateTime) {
        User user = getUser(phoneNumber, registeredDateTime);
        //TODO user 연관관계 매핑하기

        List<OrderCreateRequest.OrderProductRequest> orderProducts = request.getOrderProducts();
        List<Integer> productIds = orderProducts.stream()
                .map(OrderCreateRequest.OrderProductRequest::getProductId)
                .collect(Collectors.toList());
        List<ProductWithQuantity> productWithQuantities = getProductWithQuantity(productIds, orderProducts);

        Order savedOrder = orderRepository.save(Order.createOrder(productWithQuantities, registeredDateTime, user));
        return OrderResponse.of(savedOrder);
    }

    private List<ProductWithQuantity> getProductWithQuantity(List<Integer> productIds, List<OrderCreateRequest.OrderProductRequest> orderProducts) {
        List<Product> products = productRepository.findAllByIdIn(productIds);

        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        return orderProducts.stream()
                .map(orderProduct -> new ProductWithQuantity(productMap.get(orderProduct.getProductId()), orderProduct.getQuantity()))
                .collect(Collectors.toList());
    }

    private User getUser(String phoneNumber, LocalDateTime registeredDateTime) {
        if (StringUtils.hasText(phoneNumber)) {
            return userRepository.findByUserPhoneNumber(phoneNumber)
                    .orElseGet(() -> {
                        User newUser = createUser(phoneNumber, registeredDateTime);
                        return userRepository.save(newUser);
                    });
        }
        return null;
    }

    private User createUser(String phoneNumber, LocalDateTime registeredDateTime) {
        return User.builder()
                .userPhoneNumber(phoneNumber)
                .userPoint(UserConst.DEFAULT_POINT)
                .userJoinDate(registeredDateTime)
                .build();
    }
}

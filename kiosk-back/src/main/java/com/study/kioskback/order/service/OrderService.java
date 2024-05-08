package com.study.kioskback.order.service;

import com.study.kioskback.order.domain.Order;
import com.study.kioskback.order.repository.OrderRepository;
import com.study.kioskback.order.service.request.OrderCreateServiceRequest;
import com.study.kioskback.order.service.request.ProductWithQuantity;
import com.study.kioskback.order.service.response.OrderResponse;
import com.study.kioskback.product.domain.Product;
import com.study.kioskback.product.repository.ProductRepository;
import com.study.kioskback.user.domain.User;
import com.study.kioskback.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.study.kioskback.order.controller.request.OrderCreateRequest.*;
import static com.study.kioskback.user.domain.UserConst.*;

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

        List<OrderProductRequest> orderProducts = request.getOrderProducts();
        List<Integer> productIds = orderProducts.stream()
                .map(OrderProductRequest::getProductId)
                .collect(Collectors.toList());
        List<ProductWithQuantity> productWithQuantities = getProductWithQuantity(productIds, orderProducts);

        Order savedOrder = orderRepository.save(Order.createOrder(productWithQuantities, registeredDateTime));
        return OrderResponse.of(savedOrder);
    }

    private List<ProductWithQuantity> getProductWithQuantity(List<Integer> productIds, List<OrderProductRequest> orderProducts) {
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
                .userPoint(DEFAULT_POINT)
                .userJoinDate(registeredDateTime.toLocalDate())
                .build();
    }
}

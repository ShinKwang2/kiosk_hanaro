package com.study.kioskback.order.service;

import com.study.kioskback.order.controller.request.OrderCreateRequest;
import com.study.kioskback.order.service.request.OrderCreateServiceRequest;
import com.study.kioskback.order.service.response.OrderResponse;
import com.study.kioskback.product.domain.Product;
import com.study.kioskback.product.dto.ProductRequestDto;
import com.study.kioskback.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    private final String KOREAN_NAME = "더블 빅맥";
    private final String ENGLISH_NAME = "Double Big Mac";

    @DisplayName("주문 생성")
    @Test
    void createOrder() throws Exception {
        //given
        List<Product> products = IntStream.range(1, 21)
                .mapToObj(i -> createProduct(KOREAN_NAME + i, ENGLISH_NAME + i, 1000 * i))
                .collect(Collectors.toList());
        productRepository.saveAll(products);

        List<OrderCreateRequest.OrderProductRequest> requests = IntStream.range(5, 11)
                .mapToObj(i -> OrderCreateRequest.OrderProductRequest.builder().productId(i).quantity(i).build())
                .collect(Collectors.toList());

        OrderCreateServiceRequest serviceRequest = OrderCreateServiceRequest.builder()
                .productRequests(requests)
                .build();
        //when
        OrderResponse orderResponse = orderService.createOrder("01011111111", serviceRequest, LocalDateTime.now());

        //then
        System.out.println(orderResponse);

    }



    private Product createProduct(String koreanName, String englishName, int price) {
        return Product.builder()
                .koreanName(koreanName)
                .englishName(englishName)
                .price(price)
                .build();
    }
}
package com.study.kioskback.api.order.service;

import com.study.kioskback.api.order.controller.request.OrderCreateRequest;
import com.study.kioskback.api.order.service.request.OrderCreateServiceRequest;
import com.study.kioskback.api.order.service.response.OrderResponse;
import com.study.kioskback.api.product.domain.Product;
import com.study.kioskback.api.product.repository.ProductRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

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
        LocalDateTime now = LocalDateTime.now();
        OrderResponse orderResponse = orderService.createOrder("01011111111", serviceRequest, now);

        //then
        assertThat(orderResponse.getId()).isEqualTo(1);
        assertThat(orderResponse.getRegisteredDateTime()).isEqualTo(now);
        assertThat(orderResponse.getProducts()).hasSize(6)
                .extracting("id")
                .isEqualTo(List.of(5, 6, 7, 8, 9, 10));
    }

    private Product createProduct(String koreanName, String englishName, int price) {
        return Product.builder()
                .koreanName(koreanName)
                .englishName(englishName)
                .price(price)
                .build();
    }
}
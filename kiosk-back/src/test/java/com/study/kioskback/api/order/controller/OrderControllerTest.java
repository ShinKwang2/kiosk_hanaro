package com.study.kioskback.api.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.kioskback.api.order.controller.request.OrderCreateRequest;
import com.study.kioskback.api.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.study.kioskback.api.order.controller.request.OrderCreateRequest.OrderProductRequest;
import static com.study.kioskback.api.order.controller.request.OrderCreateRequest.builder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    OrderService orderService;


    @DisplayName("신규 주문을 등록한다.")
    @Test
    void createOrder() throws Exception {
        //given
        final String PHONE_NUMBER = "01011111111";

        OrderCreateRequest request = builder()
                .phoneNumber(PHONE_NUMBER)
                .orderProducts(List.of(OrderProductRequest.builder().productId(1).quantity(2).build()))
                .build();

        //when // then
        mockMvc.perform(post("/api/v1/orders")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("신규 주문을 등록할 때 상품번호는 1개 이상 있어야 한다.")
    @Test
    void createOrderWithEmptyProduct() throws Exception {
        //given
        final String PHONE_NUMBER = "01011111111";
        OrderCreateRequest request = builder()
                .phoneNumber(PHONE_NUMBER)
                .orderProducts(List.of())
                .build();

        //when //then
        mockMvc.perform(post("/api/v1/orders")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("주문 번호 리스트는 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
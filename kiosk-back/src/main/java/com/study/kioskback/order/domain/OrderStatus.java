package com.study.kioskback.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    INIT("주문생성"),
    RECEIVED("주문접수"),
    PAYMENT_COMPLETED("결제완료"),
    PAYMENT_FAILED("결제실패"),
    CANCELED("주문취소"),
    COMPLETED("처리완료");

    private final String text;


}

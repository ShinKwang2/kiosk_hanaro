package com.study.kioskback.error.exception.product;

import com.study.kioskback.error.exception.CustomException;

public class NotFoundProductType extends CustomException {

    private static final String MESSAGE = "해당하는 제품타입이 없습니다.";

    public NotFoundProductType() {
        super(MESSAGE);
    }

    public NotFoundProductType(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}

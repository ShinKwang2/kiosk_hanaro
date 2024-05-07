package com.study.kioskback.error.exception.product;

import com.study.kioskback.error.exception.CustomException;

public class NotFoundProduct extends CustomException {

    private static final String MESSAGE = "해당하는 제품이 없습니다.";

    public NotFoundProduct() {
        super(MESSAGE);
    }

    public NotFoundProduct(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}

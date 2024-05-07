package com.study.kioskback.error.exception.product;

import com.study.kioskback.error.exception.CustomException;

public class NotFoundProductStatus extends CustomException {

    private static final String MESSAGE = "해당하는 제품상태가 없습니다.";

    public NotFoundProductStatus() {
        super(MESSAGE);
    }

    public NotFoundProductStatus(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}

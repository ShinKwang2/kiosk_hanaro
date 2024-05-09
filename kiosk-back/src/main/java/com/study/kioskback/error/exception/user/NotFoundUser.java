package com.study.kioskback.error.exception.user;

import com.study.kioskback.error.exception.CustomException;

public class NotFoundUser extends CustomException {

    private static final String MESSAGE = "해당하는 회원이 없습니다.";
    public NotFoundUser() {
        super(MESSAGE);
    }

    public NotFoundUser(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}

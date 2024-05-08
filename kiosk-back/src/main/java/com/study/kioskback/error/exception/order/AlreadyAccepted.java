package com.study.kioskback.error.exception.order;

import com.study.kioskback.error.exception.CustomException;

import javax.swing.undo.CannotUndoException;

public class AlreadyAccepted extends CustomException {

    private final static String MESSAGE = "이미 주문이 들어간 상태입니다. 전화로 문의주시기 바랍니다";

    public AlreadyAccepted() {
        super(MESSAGE);
    }

    public AlreadyAccepted(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 410;
    }
}

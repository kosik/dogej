package org.dogej;

public enum ErrorCodes {
    Unknown(-1),
    AccessDenied(0),
    InvalidInput(10),
    InsufficentAmount(20),
    NotFound(30);


    private int value;

    ErrorCodes(final int status) {
        value = status;
    }

    public int getValue() {
        return value;
    }
}

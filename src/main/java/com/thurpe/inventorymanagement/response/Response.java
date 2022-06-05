package com.thurpe.inventorymanagement.response;

public interface Response {
    String SUCCESS_MESSAGE = "Success";
    int SUCCESS = 0;
    int FAILED = 1;

    default int getCode() {
        return SUCCESS;
    }

    default String getMessage() {
        return SUCCESS_MESSAGE;
    }
}

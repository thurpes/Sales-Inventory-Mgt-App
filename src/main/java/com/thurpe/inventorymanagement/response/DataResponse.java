package com.thurpe.inventorymanagement.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
public class DataResponse<T> implements Response {
    private T data;
    private String message;
    private int status;
}
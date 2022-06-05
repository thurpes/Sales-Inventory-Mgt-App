package com.thurpe.inventorymanagement.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class SimpleResponse implements Response {

    private String message;
    private int status;

}
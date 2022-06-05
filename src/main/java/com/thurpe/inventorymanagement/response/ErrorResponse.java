package com.thurpe.inventorymanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@Data
public class ErrorResponse {

    private List<String> errors;
}

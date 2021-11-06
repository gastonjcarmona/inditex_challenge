package com.inditex.challenge.infrastructure.adapter.inbound;

import com.inditex.challenge.app.domain.exception.ProductPriceNotFoundException;
import com.inditex.challenge.infrastructure.adapter.inbound.http.CustomControllerAdvice;
import com.inditex.challenge.infrastructure.adapter.inbound.http.pojo.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

public class CustomControllerAdviceTest {

    private final static String ERROR_MESSAGE = "test";
    private CustomControllerAdvice customControllerAdvice;

    @BeforeEach
    public void init(){
        customControllerAdvice = new CustomControllerAdvice();
    }

    @Test
    public void productPriceNotFoundException() {
        final ResponseEntity<ErrorResponse> response = customControllerAdvice.productPriceNotFoundException(new ProductPriceNotFoundException(ERROR_MESSAGE));

        final ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
        final ResponseEntity<ErrorResponse> expectedResponse = status(NOT_FOUND).body(errorResponse);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void unknownException() {
        final ResponseEntity<ErrorResponse> response = customControllerAdvice.unknownException(new RuntimeException(ERROR_MESSAGE));

        final ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MESSAGE);
        final ResponseEntity<ErrorResponse> expectedResponse = status(INTERNAL_SERVER_ERROR).body(errorResponse);

        assertEquals(expectedResponse, response);
    }
}

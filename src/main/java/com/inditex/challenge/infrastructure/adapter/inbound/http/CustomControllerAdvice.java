package com.inditex.challenge.infrastructure.adapter.inbound.http;

import com.inditex.challenge.app.domain.exception.ProductPriceNotFoundException;
import com.inditex.challenge.infrastructure.adapter.inbound.http.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String VALIDATION_EXCEPTION_MESSAGE = "Validation exception: '{}'";

    /**
     * Handle entity exceptions.
     */
    @ExceptionHandler({ProductPriceNotFoundException.class})
    public ResponseEntity<ErrorResponse> productPriceNotFoundException(final ProductPriceNotFoundException e) {
        log.warn("Product Price Not Found Exception", e);
        return status(NOT_FOUND).body(ErrorResponse.builder().status(NOT_FOUND).message(e.getMessage()).build());
    }


    /**
     * Handle unknown exceptions.
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> unknownException(final Exception e) {
        log.error("Unknown exception", e);
        return status(INTERNAL_SERVER_ERROR).body(ErrorResponse.builder().status(INTERNAL_SERVER_ERROR).message(e.getMessage()).build());
    }


    /**
     * Handle illegal field values in incoming json request.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException e,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        log.warn(VALIDATION_EXCEPTION_MESSAGE, e.getMessage());
        return badRequest().body(ErrorResponse.builder().message(e.getMessage()).status(BAD_REQUEST).build());
    }

}

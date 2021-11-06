package com.inditex.challenge.app.domain.exception;

public class ProductPriceNotFoundException extends RuntimeException {
    public ProductPriceNotFoundException(final String message) {
        super(message);
    }
}
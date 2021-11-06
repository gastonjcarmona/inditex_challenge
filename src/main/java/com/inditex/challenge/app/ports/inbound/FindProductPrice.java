package com.inditex.challenge.app.ports.inbound;

import com.inditex.challenge.app.domain.model.PriceTO;

import java.time.LocalDateTime;

public interface FindProductPrice {
    PriceTO findProductPrice(final LocalDateTime applicationDate, final Long productId, final Long brandId);
}

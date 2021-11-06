package com.inditex.challenge.app.usecase;

import com.inditex.challenge.app.domain.exception.ProductPriceNotFoundException;
import com.inditex.challenge.app.domain.model.PriceTO;
import com.inditex.challenge.app.ports.inbound.FindProductPrice;
import com.inditex.challenge.app.ports.outbound.ProductPriceRepository;

import java.time.LocalDateTime;
import java.util.Comparator;

public class FindProductPriceUseCase implements FindProductPrice {

    private final ProductPriceRepository repository;

    FindProductPriceUseCase(final ProductPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public PriceTO findProductPrice(final LocalDateTime applicationDate, final Long productId, final Long brandId) {
        return repository.findProductPrice(applicationDate, productId, brandId)
                .stream()
                .max(Comparator.comparing(PriceTO::getPriority))
                .orElseThrow(ProductPriceNotFoundException::new);
    }
}

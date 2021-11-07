package com.inditex.challenge.app.usecase;

import com.inditex.challenge.app.domain.exception.ProductPriceNotFoundException;
import com.inditex.challenge.app.domain.model.PriceTO;
import com.inditex.challenge.app.ports.inbound.FindProductPrice;
import com.inditex.challenge.app.ports.outbound.ProductPriceRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Comparator;

@Slf4j
public class FindProductPriceUseCase implements FindProductPrice {

    private final ProductPriceRepository repository;

    public FindProductPriceUseCase(final ProductPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public PriceTO findProductPrice(final LocalDateTime applicationDate, final Long productId, final Long brandId) {
        log.info("Trying to get price from data base for product '{}', brand '{}' and date '{}'", productId, brandId, applicationDate);
        return repository.findProductPrice(applicationDate, productId, brandId)
                .stream()
                .max(Comparator.comparing(PriceTO::getPriority))
                .orElseThrow(() -> new ProductPriceNotFoundException("Price not found"));
    }
}

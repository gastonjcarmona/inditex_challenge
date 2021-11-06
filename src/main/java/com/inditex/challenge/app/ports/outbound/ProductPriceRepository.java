package com.inditex.challenge.app.ports.outbound;

import com.inditex.challenge.app.domain.model.PriceTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPriceRepository {

    /**
     *
     * @return List of prices for specific product, brand and applicationDate.
     * If no records can be obtained the list will be empty
     */
    List<PriceTO> findProductPrice(final LocalDateTime applicationDate, final Long productId, final Long brandId);
}

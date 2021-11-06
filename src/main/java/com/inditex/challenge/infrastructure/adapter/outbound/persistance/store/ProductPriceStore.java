package com.inditex.challenge.infrastructure.adapter.outbound.persistance.store;

import com.inditex.challenge.app.domain.model.PriceTO;
import com.inditex.challenge.app.ports.outbound.ProductPriceRepository;
import com.inditex.challenge.infrastructure.adapter.outbound.persistance.entity.Price;
import com.inditex.challenge.infrastructure.adapter.outbound.persistance.repository.JpaProductPriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Slf4j
public class ProductPriceStore extends Store<Price> implements ProductPriceRepository {

    private final JpaProductPriceRepository jpaProductPriceRepository;

    public ProductPriceStore(final JpaProductPriceRepository jpaProductPriceRepository) {
        this.jpaProductPriceRepository = jpaProductPriceRepository;
    }

    @Override
    public List<PriceTO> findProductPrice(final LocalDateTime applicationDate, final Long productId, final Long brandId) {

        final Specification<Price> specification = Specification.where(equals("productId", productId))
                .and(equals("brandId", brandId))
                .and(lessThanOrEqualTo("startDate", applicationDate))
                .and(greaterThanOrEqualTo("endDate", applicationDate));

        return jpaProductPriceRepository.findAll(specification)
                .stream()
                .map(p -> {
                            final PriceTO price = new PriceTO(
                                    p.getBrandId(),
                                    p.getStartDate(),
                                    p.getEndDate(),
                                    p.getPriceList(),
                                    p.getProductId(),
                                    p.getPriority(),
                                    p.getPrice(),
                                    p.getCurrency());
                            log.info("Price selected '{}'", price);
                            return price;
                        }
                )
                .collect(Collectors.toList());
    }
}

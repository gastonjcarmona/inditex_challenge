package com.inditex.challenge.infrastructure.adapter.outbound;

import com.inditex.challenge.app.domain.model.PriceTO;
import com.inditex.challenge.app.ports.outbound.ProductPriceRepository;
import com.inditex.challenge.infrastructure.adapter.outbound.persistance.entity.Price;
import com.inditex.challenge.infrastructure.adapter.outbound.persistance.repository.JpaProductPriceRepository;
import com.inditex.challenge.infrastructure.adapter.outbound.persistance.store.ProductPriceStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.inditex.challenge.app.domain.model.Currency.EUR;
import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductPriceStoreTest {

    private final static Long PRODUCT_ID = 35455L;
    private final static Long BRAND_ID = 1L;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JpaProductPriceRepository jpaProductPriceRepository;

    private ProductPriceRepository productPriceRepository;

    private LocalDateTime sysdate;

    @BeforeEach
    public void setup() {
        sysdate = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0, 0));
        productPriceRepository = new ProductPriceStore(jpaProductPriceRepository);
        given_existing_price();
    }

    @Test
    public void find_existing_product() {
        final List<PriceTO> prices = productPriceRepository.findProductPrice(sysdate, PRODUCT_ID, BRAND_ID);
        assertEquals(1, prices.size());
    }

    @Test
    public void find_non_existing_product() {
        final List<PriceTO> prices = productPriceRepository.findProductPrice(sysdate, 2L, BRAND_ID);
        assertEquals(0, prices.size());
    }

    private void given_existing_price() {
        final Price price = new Price(BRAND_ID, sysdate.minusDays(1), sysdate.plusDays(2), 1, PRODUCT_ID, 0, ONE, EUR);
        price.setCreated(sysdate);
        entityManager.persist(price);
        entityManager.flush();
    }

}

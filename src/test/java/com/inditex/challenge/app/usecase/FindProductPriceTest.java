package com.inditex.challenge.app.usecase;

import com.inditex.challenge.ProductPriceHelper;
import com.inditex.challenge.app.domain.exception.ProductPriceNotFoundException;
import com.inditex.challenge.app.domain.model.PriceTO;
import com.inditex.challenge.app.ports.inbound.FindProductPrice;
import com.inditex.challenge.app.ports.outbound.ProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindProductPriceTest {

    private final static Long PRODUCT_ID = 35455L;
    private final static Long BRAND_ID = 1L;

    private FindProductPrice findProductPrice;
    private ProductPriceRepository repository;

    @BeforeEach
    public void setup() {
        repository = mock(ProductPriceRepository.class);
        findProductPrice = new FindProductPriceUseCase(repository);
    }

    @Test
    public void find_existing_product_for_day_14_and_10_hs() {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(10, 0, 0));

        final PriceTO expectedPrice = ProductPriceHelper.getPrice2();
        final List<PriceTO> expectedPrices = newArrayList(ProductPriceHelper.getPrice1(), expectedPrice, ProductPriceHelper.getPrice3(), ProductPriceHelper.getPrice4());
        when(repository.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(expectedPrices);

        then_price_is_equals_than_expected(applicationDate, expectedPrice);
    }

    @Test
    public void find_existing_product_for_day_14_and_16_hs() {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(16, 0, 0));

        final PriceTO expectedPrice = ProductPriceHelper.getPrice2();
        final List<PriceTO> expectedPrices = newArrayList(expectedPrice, ProductPriceHelper.getPrice3(), ProductPriceHelper.getPrice4());
        when(repository.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(expectedPrices);

        then_price_is_equals_than_expected(applicationDate, expectedPrice);
    }

    @Test
    public void find_existing_product_for_day_14_and_21_hs() {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(21, 0, 0));

        final PriceTO expectedPrice = ProductPriceHelper.getPrice3();
        final List<PriceTO> expectedPrices = newArrayList(expectedPrice, ProductPriceHelper.getPrice4());
        when(repository.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(expectedPrices);

        then_price_is_equals_than_expected(applicationDate, expectedPrice);
    }

    @Test
    public void find_existing_product_for_day_15_and_10_hs() {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(10, 0, 0));

        final PriceTO expectedPrice = ProductPriceHelper.getPrice3();
        final List<PriceTO> expectedPrices = newArrayList(expectedPrice, ProductPriceHelper.getPrice4());
        when(repository.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(expectedPrices);

        then_price_is_equals_than_expected(applicationDate, expectedPrice);
    }

    @Test
    public void find_existing_product_for_day_16_and_21_hs() {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(10, 0, 0));

        final PriceTO expectedPrice = ProductPriceHelper.getPrice4();
        final List<PriceTO> expectedPrices = newArrayList(expectedPrice);
        when(repository.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(expectedPrices);

        then_price_is_equals_than_expected(applicationDate, expectedPrice);
    }

    @Test
    public void find_non_existing_product_throws_exception() {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(10, 0, 0));

        when(repository.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(newArrayList());

        assertThrows(ProductPriceNotFoundException.class, () -> findProductPrice.findProductPrice(applicationDate, PRODUCT_ID, BRAND_ID));
    }

    private void then_price_is_equals_than_expected(final LocalDateTime applicationDate, final PriceTO expectedPrice) {
        final PriceTO priceResult = findProductPrice.findProductPrice(applicationDate, PRODUCT_ID, BRAND_ID);
        assertNotNull(priceResult);
        assertEquals(expectedPrice, priceResult);
    }
}

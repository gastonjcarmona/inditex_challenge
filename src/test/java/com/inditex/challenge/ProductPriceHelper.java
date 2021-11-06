package com.inditex.challenge;

import com.inditex.challenge.app.domain.model.PriceTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.inditex.challenge.app.domain.model.Currency.EUR;

public class ProductPriceHelper {

    public static PriceTO getPrice1() {
        return PriceTO.builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(0, 0, 0)))
                .endDate(LocalDateTime.of(LocalDate.of(2020, 12, 31), LocalTime.of(23, 59, 59)))
                .priceList(1)
                .productId(35455L)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency(EUR)
                .build();
    }

    public static PriceTO getPrice2() {
        return PriceTO.builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(15, 0, 0)))
                .endDate(LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(18, 30, 0)))
                .priceList(2)
                .productId(35455L)
                .priority(1)
                .price(BigDecimal.valueOf(25.40))
                .currency(EUR)
                .build();
    }

    public static PriceTO getPrice3() {
        return PriceTO.builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(0, 0, 0)))
                .endDate(LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(11, 0, 0)))
                .priceList(2)
                .productId(35455L)
                .priority(1)
                .price(BigDecimal.valueOf(25.40))
                .currency(EUR)
                .build();
    }

    public static PriceTO getPrice4() {
        return PriceTO.builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(16, 0, 0)))
                .endDate(LocalDateTime.of(LocalDate.of(2020, 12, 31), LocalTime.of(23, 59, 59)))
                .priceList(2)
                .productId(35455L)
                .priority(1)
                .price(BigDecimal.valueOf(25.40))
                .currency(EUR)
                .build();
    }
}

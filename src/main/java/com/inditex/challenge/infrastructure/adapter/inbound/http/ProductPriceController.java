package com.inditex.challenge.infrastructure.adapter.inbound.http;

import com.inditex.challenge.app.domain.model.PriceTO;
import com.inditex.challenge.app.ports.inbound.FindProductPrice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/prices")
@Slf4j
@Api(tags = "Product Prices")
public class ProductPriceController {

    private final FindProductPrice findProductPrice;

    public ProductPriceController(final FindProductPrice findProductPrice) {
        this.findProductPrice = findProductPrice;
    }

    @ApiOperation("Find Product Price")
    @GetMapping("brands/{brandId}/products/{productId}")
    public PriceTO findProductPrice(@PathVariable final Long brandId,
                                    @PathVariable final Long productId,
                                    @RequestParam final String applicationDate) {

        final LocalDateTime appDate = LocalDateTime.parse(applicationDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        log.info("Find price for product '{}' of brand '{}' for date '{}'", productId, brandId, appDate);
        return findProductPrice.findProductPrice(appDate, productId, brandId);
    }
}

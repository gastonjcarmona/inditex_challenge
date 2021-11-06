package com.inditex.challenge.infrastructure.adapter.inbound;

import com.inditex.challenge.ProductPriceHelper;
import com.inditex.challenge.app.ports.inbound.FindProductPrice;
import com.inditex.challenge.infrastructure.adapter.inbound.http.ProductPriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ProductPriceControllerTest {

    private final static Long PRODUCT_ID = 35455L;
    private final static Long BRAND_ID = 1L;

    private MockMvc mockMvc;

    private FindProductPrice findProductPrice;

    @BeforeEach
    public void setup() {
        findProductPrice = mock(FindProductPrice.class);
        final ProductPriceController productPriceController = new ProductPriceController(findProductPrice);

        mockMvc = standaloneSetup(productPriceController).build();
    }

    @Test
    public void find_price_successfully() throws Exception {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(10, 10, 10));
        when(findProductPrice.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(ProductPriceHelper.getPrice1());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/prices/brands/1/products/35455?applicationDate=2020-06-14T10:10:10"))
                .andExpect(status().isOk());
    }

    @Test
    public void find_price_bad_request() throws Exception {
        final LocalDateTime applicationDate = LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(10, 10, 10));
        when(findProductPrice.findProductPrice(eq(applicationDate), eq(PRODUCT_ID), eq(BRAND_ID))).thenReturn(ProductPriceHelper.getPrice1());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/prices/brands/1/products/35455"))
                .andExpect(status().isBadRequest());
    }


}

package com.inditex.challenge.infrastructure.config;

import com.inditex.challenge.app.ports.inbound.FindProductPrice;
import com.inditex.challenge.app.ports.outbound.ProductPriceRepository;
import com.inditex.challenge.app.usecase.FindProductPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductPriceConfig {

    @Bean
    public FindProductPrice findProductPrice(final ProductPriceRepository productPriceRepository) {
        return new FindProductPriceUseCase(productPriceRepository);
    }
}

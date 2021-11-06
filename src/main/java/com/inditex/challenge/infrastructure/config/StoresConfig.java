package com.inditex.challenge.infrastructure.config;

import com.inditex.challenge.app.ports.outbound.ProductPriceRepository;
import com.inditex.challenge.infrastructure.adapter.outbound.persistance.repository.JpaProductPriceRepository;
import com.inditex.challenge.infrastructure.adapter.outbound.persistance.store.ProductPriceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoresConfig {

    @Autowired
    private JpaProductPriceRepository jpaProductPriceRepository;

    @Bean
    public ProductPriceRepository productPriceRepository() {
        return new ProductPriceStore(jpaProductPriceRepository);
    }
}

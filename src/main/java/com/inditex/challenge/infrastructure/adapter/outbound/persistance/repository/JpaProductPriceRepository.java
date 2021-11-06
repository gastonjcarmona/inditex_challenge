package com.inditex.challenge.infrastructure.adapter.outbound.persistance.repository;

import com.inditex.challenge.infrastructure.adapter.outbound.persistance.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaProductPriceRepository extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price> {

}

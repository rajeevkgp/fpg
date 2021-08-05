package com.floating.group.interview.repository;

import com.floating.group.interview.model.CandleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandleRepository extends JpaRepository<CandleEntity, Long> {
    Optional<CandleEntity> findOneByExchangeAndCurrencyPairOrderByTimeDesc(@Param("exchange") String exchange, @Param("currencyPair") String currencyPair);

    List<CandleEntity> findByExchangeAndCurrencyPair(@Param("exchange") String exchange, @Param("currencyPair") String currencyPair);
}

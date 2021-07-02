package com.floating.group.interview.repository;

import com.floating.group.interview.model.CryptoData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoDataRepository extends JpaRepository<CryptoData, Long> {
}

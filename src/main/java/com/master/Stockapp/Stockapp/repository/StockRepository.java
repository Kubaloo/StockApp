package com.master.Stockapp.Stockapp.repository;

import com.master.Stockapp.Stockapp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}

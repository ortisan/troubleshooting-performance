package br.ortiz.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockQuoteRepository extends JpaRepository<StockQuoteEntity, Long> {
}

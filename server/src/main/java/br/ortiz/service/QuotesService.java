package br.ortiz.service;

import br.ortiz.dtos.StockQuoteDTO;
import br.ortiz.persistence.IStockQuoteRepository;
import br.ortiz.persistence.StockQuoteEntity;
import br.ortiz.util.Constants;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class QuotesService {

    @Autowired
    private IStockQuoteRepository stockQuoteRepository;

    public StockQuoteDTO save(StockQuoteDTO stockQuoteDTO) {
        StockQuoteEntity stockQuoteEntity = toEntity(stockQuoteDTO);
        StockQuoteEntity savedEntity = stockQuoteRepository.save(stockQuoteEntity);
        return toDto(savedEntity);
    }

    @Cacheable("stocks")
    public List<StockQuoteDTO> find(String symbol) {
        Example<StockQuoteEntity> bySymbol = Example.of(StockQuoteEntity.builder().symbol(symbol).build());
        List<StockQuoteEntity> allBySymbol = stockQuoteRepository.findAll(bySymbol);
        return allBySymbol.stream().map(stockQuoteEntity -> toDto(stockQuoteEntity)).collect(Collectors.toList());
    }

//    @Cacheable("stocks")
    @CircuitBreaker(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME, fallbackMethod = "fallbackFind")
//    @RateLimiter(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
//    @Bulkhead(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
//    @Retry(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME, fallbackMethod = "fallbackFind")
    @TimeLimiter(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
    public CompletableFuture<List<StockQuoteDTO>> findCircuitBreak(String symbol) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (Exception exc) {

            }
            Example<StockQuoteEntity> bySymbol = Example.of(StockQuoteEntity.builder().symbol(symbol).build());
            List<StockQuoteEntity> allBySymbol = stockQuoteRepository.findAll(bySymbol);
            return allBySymbol.stream().map(stockQuoteEntity -> toDto(stockQuoteEntity)).collect(Collectors.toList());
        });
    }

    public CompletableFuture<List<StockQuoteDTO>> fallbackFind(String symbol, Throwable exception) {
        System.out.println("Fallback by timeout");
        if (true) {
            throw new RuntimeException("Fallback GET");
        }
        return null;
    }

    public StockQuoteEntity toEntity(StockQuoteDTO stockQuoteDTO) {
        return StockQuoteEntity.builder().date(stockQuoteDTO.getDate()).symbol(stockQuoteDTO.getSymbol()).open(stockQuoteDTO.getOpen()).close(stockQuoteDTO.getClose()).high(stockQuoteDTO.getHigh()).low(stockQuoteDTO.getLow()).volume(stockQuoteDTO.getVolume())
                .build();
    }

    public StockQuoteDTO toDto(StockQuoteEntity entity) {
        return StockQuoteDTO.builder().id(entity.getId()).date(entity.getDate()).symbol(entity.getSymbol()).open(entity.getOpen()).close(entity.getClose()).high(entity.getHigh()).low(entity.getLow()).volume(entity.getVolume())
                .build();

    }


}

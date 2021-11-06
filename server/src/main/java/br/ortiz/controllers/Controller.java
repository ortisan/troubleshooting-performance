package br.ortiz.controllers;

import br.ortiz.dtos.StockQuoteDTO;
import br.ortiz.service.QuotesService;
import br.ortiz.util.Constants;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {

    @Autowired
    private QuotesService quotesService;

    @PostMapping("/stock-quotes-circuit-break")
    @CircuitBreaker(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME, fallbackMethod = "fallbackPost")
    @RateLimiter(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
    @Bulkhead(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
    @Retry(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME, fallbackMethod = "fallbackPost")
    @TimeLimiter(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
    public StockQuoteDTO saveQuoteCircuitBreak(@RequestBody StockQuoteDTO stockQuoteDTO) {
        try {
            Thread.sleep(5000);
        } catch (Exception exc) {

        }
        return quotesService.save(stockQuoteDTO);
    }

    public StockQuoteDTO fallbackPost(@RequestBody StockQuoteDTO stockQuoteDTO, Throwable error) {
        if (true) {
            throw new RuntimeException("Fallback POST");
        }
        return null;
    }

    @GetMapping("/stock-quotes-circuit-break")
    @CircuitBreaker(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME, fallbackMethod = "fallbackGet")
    @RateLimiter(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
    @Bulkhead(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
    @Retry(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME, fallbackMethod = "fallbackGet")
    @TimeLimiter(name = Constants.CIRCUIT_BREAKER_BACKEND_NAME)
    public CompletableFuture<List<StockQuoteDTO>> getAllBySymbolCircuitBreak(@RequestParam(required = true) String symbol) {
        return quotesService.findCircuitBreak(symbol);
    }

    public CompletableFuture<List<StockQuoteDTO>> fallbackGet(String symbol, Throwable error) {
        if (true) {
            throw new RuntimeException("Fallback GET");
        }
        return null;
    }


    @PostMapping("/stock-quotes")
    public StockQuoteDTO saveQuote(@RequestBody StockQuoteDTO stockQuoteDTO) {
//        int randomNumber = new Random().ints(0, 100).findFirst().getAsInt();
//        if (randomNumber <= 50) {
//            throw new RuntimeException("Erro randomico para testes do dashboard grafana");
//        }
        return quotesService.save(stockQuoteDTO);
    }

    @GetMapping("/stock-quotes")
    public List<StockQuoteDTO> getAllBySymbol(@RequestParam(required = true) String symbol) {
        int randomNumber = new Random().ints(0, 100).findFirst().getAsInt();
        if (randomNumber <= 50) {
            throw new RuntimeException("Erro randomico para testes do dashboard grafana");
        }
        return quotesService.find(symbol);
    }
}


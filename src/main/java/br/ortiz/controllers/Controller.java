package br.ortiz.controllers;

import br.ortiz.dtos.StockQuoteDTO;
import br.ortiz.service.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private QuotesService quotesService;

    @PostMapping("/stock-quotes")
    public StockQuoteDTO saveQuote(@RequestBody StockQuoteDTO stockQuoteDTO) {
        return quotesService.save(stockQuoteDTO);
    }

    @GetMapping("/stock-quotes")
    public List<StockQuoteDTO> getAllBySymbol(@RequestParam(required = true) String symbol) {
        return quotesService.find(symbol);
    }
}


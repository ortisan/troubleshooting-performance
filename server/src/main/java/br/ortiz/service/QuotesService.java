package br.ortiz.service;

import br.ortiz.dtos.StockQuoteDTO;
import br.ortiz.persistence.IStockQuoteRepository;
import br.ortiz.persistence.StockQuoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public StockQuoteEntity toEntity(StockQuoteDTO stockQuoteDTO) {
        return StockQuoteEntity.builder().date(stockQuoteDTO.getDate()).symbol(stockQuoteDTO.getSymbol()).open(stockQuoteDTO.getOpen()).close(stockQuoteDTO.getClose()).high(stockQuoteDTO.getHigh()).low(stockQuoteDTO.getLow()).volume(stockQuoteDTO.getVolume())
                .build();
    }

    public StockQuoteDTO toDto(StockQuoteEntity entity) {
        return StockQuoteDTO.builder().id(entity.getId()).date(entity.getDate()).symbol(entity.getSymbol()).open(entity.getOpen()).close(entity.getClose()).high(entity.getHigh()).low(entity.getLow()).volume(entity.getVolume())
                .build();

    }


}

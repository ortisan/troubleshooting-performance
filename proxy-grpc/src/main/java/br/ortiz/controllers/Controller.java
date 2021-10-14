package br.ortiz.controllers;

import br.ortiz.dtos.StockQuoteDTO;
import br.ortiz.grpc.services.QuoteMessage;
import br.ortiz.grpc.services.StockServiceGrpc;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class Controller {

    @GrpcClient("stocks-service")
    private StockServiceGrpc.StockServiceBlockingStub stocksServiceStub;

    @PostMapping("/proxy-stock-quotes")
    public StockQuoteDTO saveQuote(@RequestBody StockQuoteDTO stockQuoteDTO) {
        QuoteMessage quoteMessage = stocksServiceStub.insert(toGrpc(stockQuoteDTO));
        return toDto(quoteMessage);
    }

    private StockQuoteDTO toDto(QuoteMessage quoteMessage) {
        StockQuoteDTO quoteDTO = StockQuoteDTO.builder()
                .id(quoteMessage.getId().getValue())
                .symbol(quoteMessage.getSymbol().getValue())
                .date(LocalDateTime.ofInstant(Instant.ofEpochMilli(quoteMessage.getDate().getValue()), ZoneOffset.UTC))
                .open(BigDecimal.valueOf(quoteMessage.getOpen().getValue()))
                .close(BigDecimal.valueOf(quoteMessage.getClose().getValue()))
                .high(BigDecimal.valueOf(quoteMessage.getHigh().getValue()))
                .low(BigDecimal.valueOf(quoteMessage.getLow().getValue()))
                .volume(BigDecimal.valueOf(quoteMessage.getVolume().getValue()))
                .build();

        return quoteDTO;
    }


    private QuoteMessage toGrpc(StockQuoteDTO stockQuoteDTO) {
        QuoteMessage response = QuoteMessage.newBuilder()
                .setDate(Int64Value.of(stockQuoteDTO.getDate().toInstant(ZoneOffset.UTC).toEpochMilli()))
                .setSymbol(StringValue.of(stockQuoteDTO.getSymbol()))
                .setOpen(DoubleValue.of(stockQuoteDTO.getOpen().doubleValue()))
                .setClose(DoubleValue.of(stockQuoteDTO.getClose().doubleValue()))
                .setHigh(DoubleValue.of(stockQuoteDTO.getHigh().doubleValue()))
                .setLow(DoubleValue.of(stockQuoteDTO.getLow().doubleValue()))
                .setVolume(DoubleValue.of(stockQuoteDTO.getVolume().doubleValue()))
                .build();

        return response;
    }
}


package br.ortiz.controllers;

import br.ortiz.dtos.StockQuoteDTO;
import br.ortiz.grpc.services.QuoteMessage;
import br.ortiz.grpc.services.QuoteQuery;
import br.ortiz.grpc.services.StockServiceGrpc;
import br.ortiz.service.QuotesService;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.Metadata;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.reflection.v1alpha.ErrorResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;

@GrpcService
public class ContollerGrpc extends StockServiceGrpc.StockServiceImplBase {

    @Autowired
    private QuotesService quotesService;


    @Override
    public void get(QuoteQuery request, StreamObserver<QuoteMessage> responseObserver) {
        List<StockQuoteDTO> stockQuoteDTOS = quotesService.find(request.getSymbol().getValue());

        for (StockQuoteDTO stockQuoteDTO : stockQuoteDTOS) {
            QuoteMessage quoteMessage = toGrpc(stockQuoteDTO);
            responseObserver.onNext(quoteMessage);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void insert(QuoteMessage request, StreamObserver<QuoteMessage> responseObserver) {
        int randomNumber = new Random().ints(0, 100).findFirst().getAsInt();
        if (randomNumber <= 50) {
            Metadata.Key<ErrorResponse> errorResponseKey = ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance());
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("Random error")
                    .asException());
        } else {
            StockQuoteDTO quoteDTO = toDto(request);
            StockQuoteDTO savedStockQuote = quotesService.save(quoteDTO);
            responseObserver.onNext(toGrpc(savedStockQuote));
            responseObserver.onCompleted();
        }
    }

    private StockQuoteDTO toDto(QuoteMessage quoteMessage) {
        StockQuoteDTO quoteDTO = StockQuoteDTO.builder()
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
                .setId(Int64Value.of(stockQuoteDTO.getId()))
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

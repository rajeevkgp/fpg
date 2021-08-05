package com.floating.group.interview.service;

import com.floating.group.interview.dto.CandleRequest;
import com.floating.group.interview.dto.CandleResponse;
import com.floating.group.interview.lib.CandleData;
import com.floating.group.interview.model.CandleEntity;
import com.floating.group.interview.repository.CandleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinbaseExchangeService {

    @Autowired
    private CandleData candleData;

    @Autowired
    private CandleRepository candleRepository;

    public List<CandleResponse> getCandleResponse(CandleRequest candleRequest) {
        Optional<CandleEntity> currencyPair = candleRepository.findOneByExchangeAndCurrencyPairOrderByTimeDesc(candleRequest.exchange, candleRequest.currencyPair);
        if (currencyPair.isPresent()) {
            if (Long.parseLong(candleRequest.end_timestamp) < currencyPair.get().getTime()) {
                // fetch all response from database
                candleRepository.findByExchangeAndCurrencyPair(candleRequest.exchange, candleRequest.currencyPair);
            } else {
                // fetch partial response from coinbase exchange
                Long startTime = currencyPair.get().getTime() + 1000; // 1sec
                Long endTime = Long.parseLong(candleRequest.end_timestamp);
                candleRequest.start_timestamp = startTime;
                candleRequest.end_timestamp = endTime;
                List<CandleResponse> candleResponseFromExc = candleData.getCandleResponse(candleRequest);
                candleRepository.saveAll(candleResponseFromExc);

                // fetch partial response from database
                List<CandleEntity> candleDataFromDatabase = candleRepository.findByExchangeAndCurrencyPair(candleRequest.exchange, candleRequest.currencyPair);
                return candleResponseFromExc.addAll(candleDataFromDatabase);
            }
        }
        List<CandleResponse> candleResponseFromExc = candleData.getCandleResponse(candleRequest);
        candleRepository.saveAll(candleResponseFromExc);
        // skipping this, assuming only 5hr max data is required
        return candleData.getCandleResponse(candleRequest);
    }
}

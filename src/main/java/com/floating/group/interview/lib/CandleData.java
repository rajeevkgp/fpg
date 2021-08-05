package com.floating.group.interview.lib;

import com.floating.group.interview.dto.CandleRequest;
import com.floating.group.interview.dto.CandleResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CandleData {

    public List<CandleResponse> getCandleResponse(CandleRequest candleRequest) {
        final String uri = "https://api.exchange.coinbase.com/products/" + candleRequest.currencyPair + "/candles?start=" + candleRequest.start_timestamp + "&" + "end=" + candleRequest.end_timestamp;
        RestTemplate restTemplate = new RestTemplate();
        Object candleResponse = restTemplate.getForObject(uri, Object.class);
        return (List<CandleResponse>) candleResponse;
    }
}

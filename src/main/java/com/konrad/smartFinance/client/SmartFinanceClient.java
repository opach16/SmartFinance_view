package com.konrad.smartFinance.client;

import com.konrad.smartFinance.domain.CurrencyRates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SmartFinanceClient {

    private final RestTemplate restTemplate;

    public List<CurrencyRates> fetchCurrencyRates() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currencies")
                .build()
                .encode()
                .toUri();
        CurrencyRates[] response = restTemplate.getForObject(url, CurrencyRates[].class);
        return response != null ? Arrays.asList(response) : Collections.emptyList();
    }
}

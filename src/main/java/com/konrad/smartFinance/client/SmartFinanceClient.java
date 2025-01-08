package com.konrad.smartFinance.client;

import com.konrad.smartFinance.domain.CurrencyRates;
import com.konrad.smartFinance.domain.CurrencyTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SmartFinanceClient {

    private final RestTemplate restTemplate;

    public Set<CurrencyRates> fetchCurrencyRates() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currencies")
                .build()
                .encode()
                .toUri();
        CurrencyRates[] response = restTemplate.getForObject(url, CurrencyRates[].class);
        return response != null ? new HashSet<CurrencyRates>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<CurrencyTransaction> fetchCurrencyTransactions() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions")
                .build()
                .encode()
                .toUri();
        CurrencyTransaction[] response = restTemplate.getForObject(url, CurrencyTransaction[].class);
        return response != null ? new HashSet<CurrencyTransaction>(Arrays.asList(response)) : Collections.emptySet();
    }
}

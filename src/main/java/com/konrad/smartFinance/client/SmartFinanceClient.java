package com.konrad.smartFinance.client;

import com.konrad.smartFinance.domain.*;
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

    public Set<CryptoRates> fetchCryptoRates() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/crypto")
                .build()
                .encode()
                .toUri();
        CryptoRates[] response = restTemplate.getForObject(url, CryptoRates[].class);
        return response != null ? new HashSet<CryptoRates>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<CryptoTransaction> fetchCryptoTransactions() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/crypto-transactions")
                .build()
                .encode()
                .toUri();
        CryptoTransaction[] response = restTemplate.getForObject(url, CryptoTransaction[].class);
        return response != null ? new HashSet<CryptoTransaction>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<Transaction> fetchTransactions() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/accounts/1/transactions")
                .build()
                .encode()
                .toUri();
        Transaction[] response = restTemplate.getForObject(url, Transaction[].class);
        return response != null ? new HashSet<Transaction>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<Asset> fetchAssets() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/assets")
                .build()
                .encode()
                .toUri();
        Asset[] response = restTemplate.getForObject(url, Asset[].class);
        return response != null ? new HashSet<>(Arrays.asList(response)) : Collections.emptySet();
    }

    public void addCurrencyTransactionWithParameters(CurrencyTransaction currencyTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions")
                .queryParam("userId", 1)
                .queryParam("transactionType", currencyTransaction.getTransactionType())
                .queryParam("currency", currencyTransaction.getSymbol())
                .queryParam("amount", currencyTransaction.getAmount())
                .queryParam("price", currencyTransaction.getPrice())
                .queryParam("transactionDate", currencyTransaction.getTransactionDate())
                .build()
                .encode()
                .toUri();
        restTemplate.postForObject(url, null, CurrencyTransaction.class);
    }

    public void updateCurrencyTransactionWithParams(CurrencyTransaction currencyTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions")
                .queryParam("userId", 1)
                .queryParam("transactionId", currencyTransaction.getTransactionId())
                .queryParam("transactionType", currencyTransaction.getTransactionType())
                .queryParam("currency", currencyTransaction.getSymbol())
                .queryParam("amount", currencyTransaction.getAmount())
                .queryParam("price", currencyTransaction.getPrice())
                .queryParam("transactionDate", currencyTransaction.getTransactionDate())
                .build()
                .encode()
                .toUri();
        restTemplate.put(url, null);
    }

    public void deleteCurrencyTransactionWithParams(CurrencyTransaction currencyTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions")
                .queryParam("transactionId", currencyTransaction.getTransactionId())
                .build()
                .encode()
                .toUri();
        restTemplate.delete(url);
    }
}

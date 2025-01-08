package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CurrencyRates;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Getter
public class CurrencyService {

    private final Set<CurrencyRates> currencyRatesSet;
    private static CurrencyService currencyService;
    private final SmartFinanceClient client = new SmartFinanceClient(new RestTemplate());

    private CurrencyService() {
        currencyRatesSet = addCurrencyRates();
    }

    public static CurrencyService getInstance() {
        if (currencyService == null) {
            currencyService = new CurrencyService();
        }
        return currencyService;
    }

    public Set<CurrencyRates> getCurrencyRatesSet() {
        return new HashSet<>(currencyRatesSet);
    }

    private Set<CurrencyRates> addCurrencyRates() {
        return client.fetchCurrencyRates();
    }
}

package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CurrencyRates;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Getter
@Service
public class CurrencyService {

    private final SmartFinanceClient smartFinanceClient;
    private static CurrencyService currencyService;
    private Set<CurrencyRates> currencyRates;

    private CurrencyService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        updateCurrencyRates();
    }

    public static CurrencyService getInstance() {
        if (currencyService == null) {
            currencyService = new CurrencyService();
        }
        return currencyService;
    }

    private void updateCurrencyRates() {
        currencyRates = smartFinanceClient.fetchCurrencyRates();
    }
}

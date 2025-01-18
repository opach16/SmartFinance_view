package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CryptoRates;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Getter
@Service
public class CryptoService {

    private final SmartFinanceClient smartFinanceClient;
    private static CryptoService cryptoService;
    private Set<CryptoRates> cryptoRates;

    private CryptoService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        cryptoRates = new HashSet<>();
    }

    public static CryptoService getInstance() {
        if (cryptoService == null) {
            cryptoService = new CryptoService();
        }
        return cryptoService;
    }

    public void updateRates() {
        cryptoRates = smartFinanceClient.fetchCryptoRates();
    }
}

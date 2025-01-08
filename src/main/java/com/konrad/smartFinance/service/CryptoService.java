package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CryptoRates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private final SmartFinanceClient smartFinanceClient;

    public Set<CryptoRates> getCryptoRates() {
        return smartFinanceClient.fetchCryptoRates();
    }
}

package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CryptoTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CryptoTransactionService {

    private final SmartFinanceClient smartFinanceClient;

    public Set<CryptoTransaction> getCryptoTransactions() {
        return smartFinanceClient.fetchCryptoTransactions();
    }
}

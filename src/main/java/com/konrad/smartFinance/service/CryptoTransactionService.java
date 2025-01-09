package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CryptoTransaction;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Getter
@Service
public class CryptoTransactionService {

    private final SmartFinanceClient smartFinanceClient;
    private static CryptoTransactionService cryptoTransactionService;
    private Set<CryptoTransaction> cryptoTransactions;

    private CryptoTransactionService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        updateCryptoTransactions();
    }

    public static CryptoTransactionService getInstance() {
        if (cryptoTransactionService == null) {
            cryptoTransactionService = new CryptoTransactionService();
        }
        return cryptoTransactionService;
    }

    public void updateCryptoTransactions() {
        cryptoTransactions = smartFinanceClient.fetchCryptoTransactions();
    }
}

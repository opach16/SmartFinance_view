package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.Transaction;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.logging.Logger;

@Getter
@Service
public class TransactionService {

    private final SmartFinanceClient smartFinanceClient;
    private static TransactionService transactionService;
    private Set<Transaction> transactions;

    private TransactionService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        updateTransactions();
    }

    public static TransactionService getInstance() {
        if (transactionService == null) {
            transactionService = new TransactionService();
        }
        return transactionService;
    }

    public void updateTransactions() {
        transactions = smartFinanceClient.fetchTransactions();
    }

    public void save(Transaction transaction) {
        if (transaction.getTransactionId() == 0) {
            smartFinanceClient.addTransactionWithParams(transaction);
        } else {
            smartFinanceClient.updateTransactionWithParams(transaction);
        }
    }

    public void delete(Transaction transaction) {
        if (transaction.getTransactionId() != 0) {
            smartFinanceClient.deleteTransactionWithParams(transaction);
        }
    }
}

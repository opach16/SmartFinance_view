package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.DebitTransaction;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Service
public class DebitTransactionService {

    private final SmartFinanceClient smartFinanceClient;
    private static DebitTransactionService debitTransactionService;
    private Set<DebitTransaction> transactions;

    private DebitTransactionService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        updateTransactions();
    }

    public static DebitTransactionService getInstance() {
        if (debitTransactionService == null) {
            debitTransactionService = new DebitTransactionService();
        }
        return debitTransactionService;
    }

    public void updateTransactions() {
        transactions = smartFinanceClient.fetchDebitTransactions();
    }

    public Set<DebitTransaction> findByName(String name) {
        return transactions.stream()
                .filter(transaction -> transaction.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public void save(DebitTransaction transaction) {
        if (transaction.getTransactionId() == null) {
            smartFinanceClient.addDebitTransaction(transaction);
        } else {
            smartFinanceClient.updateDebitTransaction(transaction);
        }
    }

    public void delete(DebitTransaction transaction) {
        if (transaction.getTransactionId() != null) {
            smartFinanceClient.deleteDebitTransaction(transaction);
        }
    }
}

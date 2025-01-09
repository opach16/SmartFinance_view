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

    Logger logger = Logger.getLogger(TransactionService.class.getName());
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

    //    @Scheduled(fixedRate = 20000)
    public void updateTransactions() {
        transactions = smartFinanceClient.fetchTransactions();
        System.out.println("Transactions fetched: " + transactions.size());
        logger.info("Fetched " + transactions.size() + " transactions");
    }

    public void save(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void delete(Transaction transaction) {
        this.transactions.remove(transaction);
    }
}

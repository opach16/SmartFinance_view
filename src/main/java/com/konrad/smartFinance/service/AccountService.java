package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final SmartFinanceClient smartFinanceClient;

    public Account getAccount() {
        return smartFinanceClient.fetchAccount();
    }
}

package com.konrad.smartFinance.client;

import com.konrad.smartFinance.domain.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SmartFinanceClient {

    private final RestTemplate restTemplate;

    public Set<CurrencyRates> fetchCurrencyRates() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currencies/user/" + getUserDetails().getUserId())
                .build()
                .encode()
                .toUri();
            CurrencyRates[] response = restTemplate.getForObject(url, CurrencyRates[].class);
            return response != null ? new HashSet<>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<CurrencyTransaction> fetchCurrencyTransactions() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions/user/" + getUserDetails().getUserId())
                .build()
                .encode()
                .toUri();
        CurrencyTransaction[] response = restTemplate.getForObject(url, CurrencyTransaction[].class);
        return response != null ? new HashSet<>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<CryptoRates> fetchCryptoRates() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/crypto/user/" + getUserDetails().getUserId())
                .build()
                .encode()
                .toUri();
        CryptoRates[] response = restTemplate.getForObject(url, CryptoRates[].class);
        return response != null ? new HashSet<>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<CryptoTransaction> fetchCryptoTransactions() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/crypto-transactions/user/" + getUserDetails().getUserId())
                .build()
                .encode()
                .toUri();
            CryptoTransaction[] response = restTemplate.getForObject(url, CryptoTransaction[].class);
            return response != null ? new HashSet<>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<DebitTransaction> fetchDebitTransactions() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/debit-transactions/" + getUserDetails().getUserId() + "/all")
                .build()
                .encode()
                .toUri();
        DebitTransaction[] response = restTemplate.getForObject(url, DebitTransaction[].class);
        return response != null ? new HashSet<>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Set<Asset> fetchAssets() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/assets/" + getUserDetails().getUserId())
                .build()
                .encode()
                .toUri();
        Asset[] response = restTemplate.getForObject(url, Asset[].class);
        return response != null ? new HashSet<>(Arrays.asList(response)) : Collections.emptySet();
    }

    public Account fetchAccount() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/accounts/" + getUserDetails().getUserId())
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(url, Account.class);
    }

    public void addCurrencyTransaction(CurrencyTransaction currencyTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions")
                .build()
                .encode()
                .toUri();
        currencyTransaction.setUserId(getUserDetails().getUserId());
        restTemplate.postForObject(url, currencyTransaction, CurrencyTransaction.class);
    }

    public void updateCurrencyTransaction(CurrencyTransaction currencyTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions")
                .build()
                .encode()
                .toUri();
        currencyTransaction.setUserId(getUserDetails().getUserId());
        restTemplate.put(url, currencyTransaction);
    }

    public void deleteCurrencyTransaction(CurrencyTransaction currencyTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/currency-transactions")
                .queryParam("transactionId", currencyTransaction.getTransactionId())
                .build()
                .encode()
                .toUri();
        currencyTransaction.setUserId(getUserDetails().getUserId());
        restTemplate.delete(url);
    }

    public void addCryptoTransaction(CryptoTransaction cryptoTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/crypto-transactions")
                .build()
                .encode()
                .toUri();
        cryptoTransaction.setUserId(getUserDetails().getUserId());
        restTemplate.postForObject(url, cryptoTransaction, CryptoTransaction.class);
    }

    public void updateCryptoTransaction(CryptoTransaction cryptoTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/crypto-transactions")
                .build()
                .encode()
                .toUri();
        cryptoTransaction.setUserId(getUserDetails().getUserId());
        restTemplate.put(url, cryptoTransaction);
    }

    public void deleteCryptoTransaction(CryptoTransaction cryptoTransaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/crypto-transactions")
                .queryParam("transactionId", cryptoTransaction.getTransactionId())
                .build()
                .encode()
                .toUri();
        cryptoTransaction.setUserId(getUserDetails().getUserId());
        restTemplate.delete(url);
    }

    public void addDebitTransaction(DebitTransaction transaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/debit-transactions")
                .build()
                .encode()
                .toUri();
        transaction.setUserId(getUserDetails().getUserId());
        restTemplate.postForObject(url, transaction, DebitTransaction.class);
    }

    public void updateDebitTransaction(DebitTransaction transaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/debit-transactions")
                .build()
                .encode()
                .toUri();
        transaction.setUserId(getUserDetails().getUserId());
        restTemplate.put(url, transaction);
    }

    public void deleteDebitTransaction(DebitTransaction transaction) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/debit-transactions")
                .queryParam("transactionId", transaction.getTransactionId())
                .build()
                .encode()
                .toUri();
        transaction.setUserId(getUserDetails().getUserId());
        restTemplate.delete(url);
    }

    public void addUser(UserRegistration userRegistration) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/users")
                .build()
                .encode()
                .toUri();
        restTemplate.postForObject(url, userRegistration, User.class);
    }

    public void login(LoginData loginData) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080/auth/login")
                .build()
                .encode()
                .toUri();
        UserDetails userDetails = restTemplate.postForObject(url, loginData, UserDetails.class);
        if (userDetails != null && !userDetails.getUsername().isEmpty()) {
            VaadinSession.getCurrent().setAttribute("username", userDetails.getUsername());
            VaadinSession.getCurrent().setAttribute("userId", userDetails.getUserId());
            Notification.show("Logged in successfully as " + userDetails.getUsername() + " ID: " + userDetails.getUserId());
        } else {
            throw new RuntimeException("Login failed: invalid username or password.");
        }
    }

    private UserDetails getUserDetails() {
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        Long userId = (Long) VaadinSession.getCurrent().getAttribute("userId");

        if (username == null || userId == null) {
            UI.getCurrent().navigate("login");
        }
        return new UserDetails(username, userId);
    }
}

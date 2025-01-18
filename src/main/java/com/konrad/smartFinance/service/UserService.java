package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.LoginData;
import com.konrad.smartFinance.domain.UserRegistration;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Getter
@Service
public class UserService {

    private final SmartFinanceClient smartFinanceClient;
    private static UserService userService;

    private UserService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void login(LoginData loginData) {
        smartFinanceClient.login(loginData);
    }

    public void save(UserRegistration userRegistration) {
        smartFinanceClient.addUser(userRegistration);
    }
}

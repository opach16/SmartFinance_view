package com.konrad.smartFinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartFinanceViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartFinanceViewApplication.class, args);
    }
}

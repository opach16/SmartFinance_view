package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.Asset;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Getter
@Service
public class AssetsService {

    private final SmartFinanceClient smartFinanceClient;
    private static AssetsService assetsService;
    private Set<Asset> assets;

    private AssetsService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        updateAssets();
    }

    public static AssetsService getInstance() {
        if (assetsService == null) {
            assetsService = new AssetsService();
        }
        return assetsService;
    }

    public void updateAssets() {
        assets = smartFinanceClient.fetchAssets();
    }
}
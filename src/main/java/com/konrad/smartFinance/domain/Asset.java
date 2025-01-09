package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {

    @JsonProperty("id")
    private Long assetId;
    @JsonProperty("assetType")
    private String assetType;
    @JsonProperty("name")
    private String symbol;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("currentValue")
    private BigDecimal currentValue;
}

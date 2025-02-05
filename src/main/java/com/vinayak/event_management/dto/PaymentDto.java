package com.vinayak.event_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class PaymentDto {

    @JsonProperty("pTransactionId")
    private Long pTransactionId;

    @JsonProperty("pAmount")
    @Min(0)
    private double pAmount;

    @JsonProperty("pMethod")
    @NotNull
    private String pMethod;

    @JsonProperty("pStatus")
    private String pStatus;
}


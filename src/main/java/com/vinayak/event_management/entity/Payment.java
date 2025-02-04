package com.vinayak.event_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pId;
    @NotNull
    Long pTransactionId;
    @Min(0)
    double pAmount;
    @NotNull
    String pMethod;
    String pStatus;

}

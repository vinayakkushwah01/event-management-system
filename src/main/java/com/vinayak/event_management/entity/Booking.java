package com.vinayak.event_management.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Booking {
    Long bId;
    LocalDateTime bDateTime;
    String bInfo;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
    
    @OneToOne
    @JoinColumn(name = "payment_id")
    Payment payment;


}

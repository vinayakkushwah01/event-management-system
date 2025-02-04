package com.vinayak.event_management.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
    private Long bId;
    private LocalDateTime bDateTime;
    private String bInfo;
    private int seatNo;


    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
    @OneToOne
    private @JoinColumn(name = "payment_id")
    Payment payment;
    
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName ="id", nullable = false)
    private  User user;


}

package com.vinayak.event_management.entity;

import java.time.LocalDateTime;
import java.util.HashMap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long eventId;


    String eName;
    String eDiscription;
    String eLocation;

    LocalDateTime eStarDateTime;
    LocalDateTime eEndDateTime;

    Double ePrice;
    String ePaymentModel;
    String status;

    int eTotalSeat;
    int eAvailableSeat;

     HashMap<Integer, Long> seatHashMap = new HashMap<>();
     void initialize(){
        for(int i = 1; i<= this.eTotalSeat; i++){
            seatHashMap.put(i, null);
        }
     }
     
     
    
}

package com.vinayak.event_management.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    private Long eventId;


    private String eName;
    private String eDiscription;
    private String eLocation;
    private String createdBy;


    private LocalDateTime eStarDateTime;
    private LocalDateTime eEndDateTime;

    private Double ePrice;
    private String ePaymentModel;
    private String status;

    private int eTotalSeat;
    private int eAvailableSeat;

    private HashMap<Integer, Long> seatHashMap = new HashMap<>();
    
    public  void initialize(){
        for(int i = 1; i<= this.eTotalSeat; i++){
            seatHashMap.put(i, null);
        }
     }


    public Event  assignSeatValue(int key, Long value) {
        if (seatHashMap.containsKey(key)) {
            seatHashMap.put(key, value);

            this.setEAvailableSeat(this.getEAvailableSeat()-1);
            return this;
        } else {
            return null;
        }
    }
     
    public List<Integer> getNullSeatKeys() {
        return seatHashMap.entrySet()
                          .stream()
                          .filter(entry -> entry.getValue() == null)
                          .map(entry -> entry.getKey())
                          .collect(Collectors.toList());
    }
     
    
}

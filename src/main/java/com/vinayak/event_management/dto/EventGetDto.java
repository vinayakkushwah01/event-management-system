package com.vinayak.event_management.dto;

import java.time.LocalDateTime;

import java.util.List;

import lombok.Data;
@Data
public class EventGetDto {

    Long eventId;

    String eventName;
    String eventDiscription;
    String eventLocation;
    String eventStatus;
    String eventCreatedBy;

    LocalDateTime eventStarDateTime;
    LocalDateTime eventEndDateTime;

    Double eventPrice;
    String eventPaymentModel;
    

    int eventTotalSeat;
    int eventAvailableSeat;
    public List<Integer> avilableSeatNo;
    
   
}

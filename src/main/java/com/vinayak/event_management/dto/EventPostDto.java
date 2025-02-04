package com.vinayak.event_management.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class EventPostDto {
    String eventName;
    String eventDiscription;
    String eventLocation;

    LocalDateTime eventStarDateTime;
    LocalDateTime eventEndDateTime;

    Double eventPrice;
    String eventPaymentModel;
    String eventStatus;

    int eventTotalSeat;

}

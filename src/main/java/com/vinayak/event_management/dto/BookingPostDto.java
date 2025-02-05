package com.vinayak.event_management.dto;

import java.time.LocalDateTime;


import lombok.Data;
@Data
public class BookingPostDto {
    
    LocalDateTime bookingDateTime;
    Long  bookingEventId;
    String bookingUserName;
    int bookingSeatNo;

    //Payment payment;
    
    
}

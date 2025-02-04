package com.vinayak.event_management.dto;

import java.time.LocalDateTime;

import com.vinayak.event_management.entity.Payment;

import lombok.Data;
@Data
public class BookingGetDto {
    Long bookingId;
    LocalDateTime bookingDateTime;
    int bookingSeatNo;
    String bookingInfo;
    EventGetDto bookingEventDto;
    Payment bookingPayment;
    UserDto bookingUserDto;
    
}

package com.vinayak.event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinayak.event_management.entity.Booking;

public interface BookingRepository extends JpaRepository <Booking,Long> {

}

package com.vinayak.event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinayak.event_management.entity.Booking;
import com.vinayak.event_management.entity.Event;
import com.vinayak.event_management.entity.User;

import java.util.List;
import java.time.LocalDateTime;


@Repository
public interface BookingRepository  extends JpaRepository <Booking, Long>{
        List<Booking> findByUser(User user);
        List<Booking> findBybInfo(String bInfo);
        List<Booking> findByEvent(Event event);
        List<Booking> findBybDateTime(LocalDateTime bDateTime);

}

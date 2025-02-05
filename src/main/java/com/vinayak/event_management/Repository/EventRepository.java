package com.vinayak.event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinayak.event_management.entity.Event;
import java.util.List;
import java.time.LocalDateTime;


@Repository
public interface EventRepository extends JpaRepository<Event, Long>  {
    List<Event> findByePaymentModel(String ePaymentModel);
    List<Event> findByeStarDateTimeGreaterThanEqual(LocalDateTime eStarDateTime);
    List<Event> findByeAvailableSeatGreaterThan(int eAvailableSeat);
    List<Event> findByCreatedBy(String createdBy);
}

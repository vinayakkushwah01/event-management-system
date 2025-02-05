package com.vinayak.event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinayak.event_management.entity.SeatData;
import java.util.List;
import java.util.Optional;


public interface SeatDataRepository extends JpaRepository<SeatData , Long> {
      @Query("SELECT DISTINCT s.eventId FROM SeatData s WHERE s.seatValue IS NULL")
    List<Long> findDistinctEventIdBySeatValueIsNull();

    Optional<SeatData> findByEventIdAndSeatKey(Long eventId, Integer seatKey);
    
    List<SeatData> findByEventIdAndSeatValueIsNull(Long eventId);
}

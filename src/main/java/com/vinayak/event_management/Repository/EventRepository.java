package com.vinayak.event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinayak.event_management.entity.Event;
@Repository
public interface EventRepository  extends JpaRepository <Event,Long>{
    
}

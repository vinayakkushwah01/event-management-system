package com.vinayak.event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinayak.event_management.entity.Payment;
import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
     List<Payment> findBypId(Long pId);
}

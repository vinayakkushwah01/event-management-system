package com.vinayak.event_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinayak.event_management.entity.OTP;



@Repository
public interface OtpRepository extends JpaRepository <OTP , Long>  {
   OTP findByEmail(String email);
}

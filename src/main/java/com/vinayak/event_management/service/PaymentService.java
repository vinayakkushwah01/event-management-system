package com.vinayak.event_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinayak.event_management.dto.PaymentDto;
import com.vinayak.event_management.entity.Payment;
import com.vinayak.event_management.repository.PaymentRepository;
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

   public  Payment makePayment(PaymentDto paymentDto){
    //System.out.println("Payment :- Make payment called" );
    //System.out.println(paymentDto);
        Payment p = new Payment();
        p.setPAmount(paymentDto.getPAmount());
        p.setPTransactionId(paymentDto.getPTransactionId());
        p.setPStatus(paymentDto.getPStatus());
        p.setPMethod(paymentDto.getPMethod());
        
        Payment pnew = paymentRepository.save(p);
        System.out.println(pnew.toString());
        return pnew;

    }
}

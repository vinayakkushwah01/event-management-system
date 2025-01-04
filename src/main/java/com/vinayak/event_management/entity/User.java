package com.vinayak.event_management.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name ;
    String email;
    String password;

    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role  role ;

    @CreationTimestamp
    private  LocalDateTime createdAt;

}

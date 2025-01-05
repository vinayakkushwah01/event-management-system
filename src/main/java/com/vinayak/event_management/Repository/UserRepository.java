package com.vinayak.event_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinayak.event_management.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email); 
}

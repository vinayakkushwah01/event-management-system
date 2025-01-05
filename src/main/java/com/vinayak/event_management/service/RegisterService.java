package com.vinayak.event_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinayak.event_management.dto.RegisterRequest;
import com.vinayak.event_management.entity.User;
import com.vinayak.event_management.repository.UserRepository;

@Service
public class RegisterService {
    
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser(RegisterRequest rU1){
        User u1 = new User();
        u1.setEmail(rU1.getEmail());
        u1.setName(rU1.getName());
        u1.setPassword(encoder.encode(rU1.getPassword()));
        u1.setRole(rU1.getRole());
        return userRepository.save(u1);
    }

}

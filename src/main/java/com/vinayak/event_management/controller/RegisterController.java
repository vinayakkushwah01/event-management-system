package com.vinayak.event_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinayak.event_management.dto.RegisterRequest;
import com.vinayak.event_management.entity.User;
import com.vinayak.event_management.service.RegisterService;

@RestController
public class RegisterController {
   
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest rReq){

        return registerService.registerUser(rReq);
    }
    
}

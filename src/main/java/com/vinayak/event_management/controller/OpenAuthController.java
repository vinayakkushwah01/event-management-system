package com.vinayak.event_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinayak.event_management.dto.EventGetDto;
import com.vinayak.event_management.dto.LoginRequest;
import com.vinayak.event_management.dto.RegisterRequest;
import com.vinayak.event_management.entity.Role;
import com.vinayak.event_management.entity.User;
import com.vinayak.event_management.repository.RoleRepository;
import com.vinayak.event_management.securityService.OpenAuthService;
import com.vinayak.event_management.service.EventService;

@RestController
public class OpenAuthController {
   
    @Autowired
    private OpenAuthService registerService;

    @Autowired
    private EventService eventService;

    // @Autowired
    // private RoleRepository roleRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest rReq){
            
        // Role r = new Role();
        // r.setName("User");
        // roleRepository.save(r);

        // Role r1 = new Role();
        // r1.setName("Admin");
        // roleRepository.save(r1);

        

        return registerService.registerUser(rReq);
    }
    @PostMapping("/login")
    public String   loginUser(@RequestBody LoginRequest lReq ){
        return registerService.verifyLogin(lReq);
    }
    
    @GetMapping("/")
    public List<EventGetDto> getAllEvents() {
        return eventService.getAllEventDto();
    }

}

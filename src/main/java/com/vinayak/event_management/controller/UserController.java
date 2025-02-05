package com.vinayak.event_management.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vinayak.event_management.dto.EventGetDto;
import com.vinayak.event_management.service.EventService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/event")
public class UserController {

    @Autowired
    private EventService eventService;


    // @GetMapping
    // public String home(HttpServletRequest request){
    //     return "ok Login"+request.getSession().getId();
    // }


    @GetMapping("/")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public List<EventGetDto> getAllEvents() {
        return eventService.getAllEventDto();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public EventGetDto getEventById(@PathVariable long id) {
        return eventService.getEventDto(id);
    }

    @GetMapping("/available-seats")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public List<EventGetDto> getEventsByAvailableSeats() {
        return eventService.getEventsByAvailableSeat();
    }

    @GetMapping("/upcoming")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public List<EventGetDto> getEventsByAvailableDate() {
        return eventService.getEventsByAvailableDate();
    }

    @GetMapping("/payment-model/{paymentModel}")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public List<EventGetDto> getEventsByPaymentModel(@PathVariable String paymentModel) {
        return eventService.getEventsByPaymentModel(paymentModel);
    }
}

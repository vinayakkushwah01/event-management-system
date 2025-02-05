package com.vinayak.event_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinayak.event_management.dto.EventGetDto;
import com.vinayak.event_management.dto.EventPostDto;
import com.vinayak.event_management.entity.Event;
import com.vinayak.event_management.service.EventService;

@RestController
@RequestMapping("/api/event/host")
public class HostControllers {

//  @GetMapping("/admin/a")
//     @PreAuthorize("hasRole('Admin')")
//     public String adminAccess() {
//         return "Hello Admin! This endpoint is restricted to admin users.";
//     }

//     @GetMapping("/user/a")
//     @PreAuthorize("hasAnyRole('Admin', 'User')")
//     public String userAccess() {
//         return "Hello User! This endpoint is accessible by both admin and user roles.";
//     }



    @Autowired
    private EventService eventService;



    @PostMapping("/register")
    @PreAuthorize("hasRole('Admin')")
    public Event createEvent(@RequestBody EventPostDto postEvent) {
        return eventService.createEvent(postEvent);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('Admin')")
    public List<EventGetDto> getAllEvents() {
        return eventService.getAllEventDto();
    }

    @GetMapping("/my-events")
    @PreAuthorize("hasRole('Admin')")
    public List<EventGetDto> getMyEvents() {
        return eventService.getMyEvents();
    }

   

    // @PutMapping("/{eventId}/seat/{key}/booking/{bookingId}")
    // public Event updateSeatValue(@PathVariable Long eventId, @PathVariable int key, @PathVariable Long bookingId) {
    //     return eventService.updateSeatValue(eventId, key, bookingId);
    // }




}

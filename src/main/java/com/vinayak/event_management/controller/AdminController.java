package com.vinayak.event_management.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {

 @GetMapping("/admin/a")
    @PreAuthorize("hasRole('Admin')")
    public String adminAccess() {
        return "Hello Admin! This endpoint is restricted to admin users.";
    }

    @GetMapping("/user/a")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public String userAccess() {
        return "Hello User! This endpoint is accessible by both admin and user roles.";
    }
}

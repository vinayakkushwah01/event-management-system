package com.vinayak.event_management.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BasicController {
    @GetMapping("/")
    public String home(HttpServletRequest request){
        return "ok Login"+request.getSession().getId();
    }
}

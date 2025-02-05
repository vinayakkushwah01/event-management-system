package com.vinayak.event_management.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vinayak.event_management.dto.BookingGetDto;
import com.vinayak.event_management.dto.BookingPostDto;
import com.vinayak.event_management.dto.EventGetDto;
import com.vinayak.event_management.dto.PaymentDto;

import com.vinayak.event_management.service.BookingService;
import com.vinayak.event_management.service.EventService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/event")
public class UserController {

    @Autowired
    private EventService eventService;
    @Autowired
    private BookingService bookingService;
   
    


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



    // @PostMapping("/event/booking")
    // @PreAuthorize("hasAnyRole('Admin', 'User')")

    // public ResponseEntity<BookingGetDto> createBooking(@RequestBody BookingPostDto bookingPostDto) {
    //     try {
    //         BookingGetDto booking = bookingService.makeBooking(bookingPostDto);
    //         return ResponseEntity.ok(booking);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(null); 
    //     }
    // }


    @PostMapping("/booking/initiate")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<String> initiateBooking(@RequestBody BookingPostDto bookingPostDto) {
        String pendingBookingId = bookingService.initiateBooking(bookingPostDto);
        return ResponseEntity.ok("Booking initiated. Pending Booking ID: " + pendingBookingId);
    }

    @PostMapping("/booking/complete")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<BookingGetDto> completeBooking(
        @RequestParam("pendingBookingId") String pendingBookingId,
        @RequestBody PaymentDto paymentDto
    ) {
        try {
            System.out.println("pending booking Id= "+ pendingBookingId);
                System.out.println("Payment Dto Befor Calling Service laye ="+paymentDto.toString());
            BookingGetDto completedBooking = bookingService.completeBooking(pendingBookingId,paymentDto);
            return ResponseEntity.ok(completedBooking);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
//     @PostMapping("/booking/complete")
// public ResponseEntity<?> completeBooking(
//     @RequestParam("pendingBookingId") String pendingBookingId,
//     HttpServletRequest request
// ) {
//     String body;
//     try{
//         body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));    
//         System.out.println("Raw Request Body: " + body);
//     } catch (IOException | java.io.IOException e) {
//         e.printStackTrace();
//     }
//     return ResponseEntity.ok("Check Logs");
// }


// @PostMapping("/booking/complete")
// public ResponseEntity<?> completeBooking(
//     @RequestParam("pendingBookingId") String pendingBookingId,
//     @RequestBody PaymentDto paymentDto
// ) {
//     if (paymentDto == null) {
//         return ResponseEntity.badRequest().body("Payment data missing.");
//     }
//     System.out.println("Received Payment DTO: " + paymentDto);
//     return ResponseEntity.ok("Payment processed.");
// }   
}

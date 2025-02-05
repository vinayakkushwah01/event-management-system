package com.vinayak.event_management.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinayak.event_management.dto.BookingGetDto;
import com.vinayak.event_management.dto.BookingPostDto;
import com.vinayak.event_management.dto.PaymentDto;
import com.vinayak.event_management.entity.Booking;
import com.vinayak.event_management.entity.Payment;
import com.vinayak.event_management.repository.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired 
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private PaymentService paymentService;



    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456";
    private Map<String, Booking> pendingBookings = new HashMap<>();


    BookingGetDto bookingToBookingGetDtoMapping(Booking b){
        BookingGetDto bookingGetDto = new BookingGetDto();

        bookingGetDto.setBookingId(b.getBId());
        bookingGetDto.setBookingDateTime(b.getBDateTime());
        bookingGetDto.setBookingPayment(b.getPayment());
        bookingGetDto.setBookingInfo(b.getBInfo());
        bookingGetDto.setBookingSeatNo(b.getSeatNo());
        bookingGetDto.setBookingEventDto(eventService.getEventDto(b.getEvent().getEventId()));
        bookingGetDto.setBookingUserDto(userService.getUserDto(b.getUser().getEmail()));
        
        return bookingGetDto;   

    }


    public String initiateBooking(BookingPostDto bookingPostDto) {
        Booking booking = new Booking();
        booking.setBDateTime(LocalDateTime.now());
        booking.setSeatNo(bookingPostDto.getBookingSeatNo());
        booking.setEvent(eventService.getEvent(bookingPostDto.getBookingEventId()));
        booking.setUser(userService.getUserFromUsername(bookingPostDto.getBookingUserName()));

        String pendingBookingId = "BK-" + System.currentTimeMillis();
        pendingBookings.put(pendingBookingId, booking);
        return pendingBookingId;
    }

    public BookingGetDto completeBooking(String pendingBookingId, PaymentDto paymentDto) {
        if (!pendingBookings.containsKey(pendingBookingId)) {
            System.out.println("Booking id not macth ");
            throw new IllegalArgumentException("Invalid booking ID.");
        }

        Booking booking = pendingBookings.remove(pendingBookingId);
        //System.out.println("booking at Paynding removed :- "+booking.toString());
        //System.out.println("###########");
       // System.out.println("Payment Dto befor calling make Payment : -"+paymentDto.toString());
        booking.setPayment(paymentService.makePayment(paymentDto));

        Booking savedBooking = bookingRepository.save(booking);
        String data = savedBooking.getBId() + "|" + booking.getSeatNo() + "|" +
                      booking.getUser().getEmail() + "|" + booking.getBDateTime() +
                      "|" + booking.getEvent().getEName();
        savedBooking.setBInfo(encrypt(data));

        savedBooking = bookingRepository.save(booking);
        return bookingToBookingGetDtoMapping(savedBooking);
    }


   
    public static String encrypt(String data) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }
    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
    
}

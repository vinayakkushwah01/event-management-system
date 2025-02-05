 package com.vinayak.event_management.service;
/*
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinayak.event_management.dto.EventGetDto;
import com.vinayak.event_management.dto.EventPostDto;
import com.vinayak.event_management.entity.Event;
import com.vinayak.event_management.repository.EventRepository;


@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;



    public EventGetDto mapToEventGetDto(Event event) {
        EventGetDto eventDto = new EventGetDto();

        eventDto.setEventId(event.getEventId());
        
        eventDto.setEventName(event.getEName());
        eventDto.setEventDiscription(event.getEDiscription());
        eventDto.setEventLocation(event.getELocation());
        eventDto.setEventStatus(event.getStatus());
        eventDto.setEventCreatedBy(event.getCreatedBy());
        eventDto.setEventStarDateTime(event.getEStarDateTime());
        eventDto.setEventEndDateTime(event.getEEndDateTime());
        eventDto.setEventPrice(event.getEPrice());
        eventDto.setEventPaymentModel(event.getEPaymentModel());
        eventDto.setEventTotalSeat(event.getETotalSeat());
        eventDto.setEventAvailableSeat(event.getEAvailableSeat());
        eventDto.setAvilableSeatNo(event.getNullSeatKeys()); 

        return eventDto;
    }



    public List<EventGetDto> mapEventListToEventDtoList(List<Event> e){
        List<EventGetDto> eventDtos = null;
        for (Event event : e) {
            eventDtos.add( mapToEventGetDto(event));
         }
         return eventDtos;
    }

    public EventGetDto getEventDto(long id){
        Event e = eventRepository.findById(id).get();
        return mapToEventGetDto(e);
    }

    public Event getEvent(long id){
        Event e = eventRepository.findById(id).get();
        return (e);
    }

    public List<EventGetDto> getAllEventDto(){
        List <Event> events = eventRepository.findAll();
        return mapEventListToEventDtoList(events);
    }


    public Event createEvent(EventPostDto postEvent){

        Event e = new Event();
        e.setEName(postEvent.getEventName());
        e.setEDiscription(postEvent.getEventDiscription());
        e.setELocation(postEvent.getEventLocation());
        e.setCreatedBy(userService.getCurrentUserUsername());

        e.setEStarDateTime(postEvent.getEventStarDateTime());
        e.setEEndDateTime(postEvent.getEventEndDateTime());

        e.setETotalSeat(postEvent.getEventTotalSeat());
        e.setEAvailableSeat(postEvent.getEventTotalSeat()-1);

        e.setEPrice(postEvent.getEventPrice());
        e.setEPaymentModel(postEvent.getEventPaymentModel().toUpperCase());
        e.setStatus( postEvent.getEventStatus());

        e.initialize();
        
        return eventRepository.save(e);
    }

    public List<EventGetDto> getMyEvents(){
       List<Event> e=  eventRepository.findByCreatedBy( userService.getCurrentUserUsername());
       return mapEventListToEventDtoList(e);
        
    }


    public List<EventGetDto> getEventsByAvilableSeat(){
        List<Event> e = eventRepository.findByeAvailableSeatGreaterThan((Integer)0);
        return mapEventListToEventDtoList(e);
    }

    public List<EventGetDto> getEventsByAvilableDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Event> e = eventRepository.findByeStarDateTimeGreaterThanEqual(currentDateTime);
        return mapEventListToEventDtoList(e);
    }

    public List<EventGetDto> getEventsByPaymentModel(String paymentModel){
        List<Event> e= eventRepository.findByePaymentModel(paymentModel.toUpperCase());
        return mapEventListToEventDtoList(e);
    }

    public Event updateSeatvalue(Long eventId ,int key , Long bookingId){
      Event e =  getEvent(eventId).assignSeatValue(key, bookingId);
        return eventRepository.save(e);
    };

    



}
 */




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinayak.event_management.dto.EventGetDto;
import com.vinayak.event_management.dto.EventPostDto;
import com.vinayak.event_management.entity.Event;
import com.vinayak.event_management.entity.SeatData;
import com.vinayak.event_management.repository.EventRepository;
import com.vinayak.event_management.repository.SeatDataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SeatDataRepository seatDataRepository;

    @Autowired
    private UserService userService;

    public EventGetDto mapToEventGetDto(Event event) {
        EventGetDto eventDto = new EventGetDto();

        eventDto.setEventId(event.getEventId());
        eventDto.setEventName(event.getEName());
        eventDto.setEventDiscription(event.getEDiscription());
        eventDto.setEventLocation(event.getELocation());
        eventDto.setEventStatus(event.getStatus());
        eventDto.setEventCreatedBy(event.getCreatedBy());
        eventDto.setEventStarDateTime(event.getEStarDateTime());
        eventDto.setEventEndDateTime(event.getEEndDateTime());
        eventDto.setEventPrice(event.getEPrice());
        eventDto.setEventPaymentModel(event.getEPaymentModel());
        eventDto.setEventTotalSeat(event.getETotalSeat());
        eventDto.setEventAvailableSeat(event.getEAvailableSeat());
        
        List<Integer> availableSeats = seatDataRepository.findByEventIdAndSeatValueIsNull(event.getEventId())
                                                         .stream()
                                                         .map(SeatData::getSeatKey)
                                                         .collect(Collectors.toList());
        eventDto.setAvilableSeatNo(availableSeats);

        return eventDto;
    }

    public List<EventGetDto> mapEventListToEventDtoList(List<Event> events) {
        return events.stream()
                     .map(this::mapToEventGetDto)
                     .collect(Collectors.toList());
    }

    public EventGetDto getEventDto(long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        return mapToEventGetDto(event);
    }

    public Event getEvent(long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public List<EventGetDto> getAllEventDto() {
        List<Event> events = eventRepository.findAll();
        return mapEventListToEventDtoList(events);
    }

    public Event createEvent(EventPostDto postEvent) {
        Event event = new Event();
        event.setEName(postEvent.getEventName());
        event.setEDiscription(postEvent.getEventDiscription());
        event.setELocation(postEvent.getEventLocation());
        event.setCreatedBy(userService.getCurrentUserUsername());
        event.setEStarDateTime(postEvent.getEventStarDateTime());
        event.setEEndDateTime(postEvent.getEventEndDateTime());
        event.setETotalSeat(postEvent.getEventTotalSeat());
        event.setEAvailableSeat(postEvent.getEventTotalSeat());
        event.setEPrice(postEvent.getEventPrice());
        event.setEPaymentModel(postEvent.getEventPaymentModel().toUpperCase());
        event.setStatus(postEvent.getEventStatus());
        
        Event savedEvent = eventRepository.save(event);

        // Initialize seat data entries
        for (int i = 1; i <= postEvent.getEventTotalSeat(); i++) {
            SeatData seatData = new SeatData();
            seatData.setEventId(savedEvent.getEventId());
            seatData.setSeatKey(i);
            seatData.setSeatValue(null);
            seatDataRepository.save(seatData);
        }

        return savedEvent;
    }

    public List<EventGetDto> getMyEvents() {
        List<Event> events = eventRepository.findByCreatedBy(userService.getCurrentUserUsername());
        return mapEventListToEventDtoList(events);
    }

    public List<EventGetDto> getEventsByAvailableSeat() {
        List<Long> eventIds = seatDataRepository.findDistinctEventIdBySeatValueIsNull();
        List<Event> events = eventRepository.findAllById(eventIds);
        return mapEventListToEventDtoList(events);
    }

    public List<EventGetDto> getEventsByAvailableDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Event> events = eventRepository.findByeStarDateTimeGreaterThanEqual(currentDateTime);
        return mapEventListToEventDtoList(events);
    }

    public List<EventGetDto> getEventsByPaymentModel(String paymentModel) {
        List<Event> events = eventRepository.findByePaymentModel(paymentModel.toUpperCase());
        return mapEventListToEventDtoList(events);
    }

    public Event updateSeatValue(Long eventId, int key, Long bookingId) {
        SeatData seatData = seatDataRepository.findByEventIdAndSeatKey(eventId, key)
                                              .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        seatData.setSeatValue(bookingId);
        seatDataRepository.save(seatData);

        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setEAvailableSeat(event.getEAvailableSeat() - 1);
        return eventRepository.save(event);
    }
}

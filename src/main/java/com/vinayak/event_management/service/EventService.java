package com.vinayak.event_management.service;

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

    EventGetDto getEventDto(long id){
        Event e = eventRepository.findById(id).get();
        return mapToEventGetDto(e);
    }

    Event getEvent(long id){
        Event e = eventRepository.findById(id).get();
        return (e);
    }

    List<EventGetDto> getAllEventDto(){
        List <Event> events = eventRepository.findAll();
        return mapEventListToEventDtoList(events);
    }


    Event createEvent(EventPostDto postEvent){

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
        return e;
    }

    List<EventGetDto> getMyEvents(){
       List<Event> e=  eventRepository.findByCreatedBy( userService.getCurrentUserUsername());
       return mapEventListToEventDtoList(e);
        
    }


    List<EventGetDto> getEventsByAvilableSeat(){
        List<Event> e = eventRepository.findByEAvailableSeatGreaterThan((Integer)0);
        return mapEventListToEventDtoList(e);
    }

    List<EventGetDto> getEventsByAvilableDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Event> e = eventRepository.findByEStarDateTimeGreaterThanEqual(currentDateTime);
        return mapEventListToEventDtoList(e);
    }

    List<EventGetDto> getEventsByPaymentModel(String paymentModel){
        List<Event> e= eventRepository.findByEPaymentModel(paymentModel.toUpperCase());
        return mapEventListToEventDtoList(e);
    }

    Event updateSeatvalue(Long eventId ,int key , Long bookingId){
      Event e =  getEvent(eventId).assignSeatValue(key, bookingId);
        return eventRepository.save(e);
    };

    



}

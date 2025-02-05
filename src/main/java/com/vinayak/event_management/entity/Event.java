package com.vinayak.event_management.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;


    private String eName;
    private String eDiscription;
    private String eLocation;
    private String createdBy;


    private LocalDateTime eStarDateTime;
    private LocalDateTime eEndDateTime;

    private Double ePrice;
    private String ePaymentModel;
    private String status;

    private int eTotalSeat;
    private int eAvailableSeat;

     @OneToMany(mappedBy = "eventId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatData> seatDataList;

    // Non-persistent field for runtime operations
    private transient Map<Integer, SeatData> seatDataMap;

    // Initialize seats by adding SeatData entries
    public void initializeSeats() {
        if (seatDataList == null) {
            seatDataList = new ArrayList<>();
        }
        seatDataMap = new HashMap<>();
        for (int i = 1; i <= eTotalSeat; i++) {
            SeatData seat = new SeatData();
            seat.setEventId(this.eventId);
            seat.setSeatKey(i);
            seat.setSeatValue(null);
            seatDataList.add(seat);
            seatDataMap.put(i, seat);
        }
    }

    public Event assignSeatValue(int key, Long bookingId) {
        if (seatDataMap == null) {
            buildSeatDataMap();
        }
        SeatData seat = seatDataMap.get(key);
        if (seat != null && seat.getSeatValue() == null) {
            seat.setSeatValue(bookingId);
            this.eAvailableSeat--;
            return this;
        }
        return null;
    }

    public List<Integer> getAvailableSeatKeys() {
        if (seatDataMap == null) {
            buildSeatDataMap();
        }
        return seatDataMap.values().stream()
                .filter(seat -> seat.getSeatValue() == null)
                .map(SeatData::getSeatKey)
                .collect(Collectors.toList());
    }

    @PostLoad
    private void buildSeatDataMap() {
        if (seatDataList != null) {
            seatDataMap = seatDataList.stream()
                    .collect(Collectors.toMap(SeatData::getSeatKey, seat -> seat));
        } else {
            seatDataMap = new HashMap<>();
        }
    }
    
}

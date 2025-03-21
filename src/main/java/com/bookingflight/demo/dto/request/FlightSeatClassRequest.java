package com.bookingflight.demo.dto.request;

import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.SeatClass;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightSeatClassRequest {
    private Flight flight;
    private SeatClass seatClass;
    private Integer quantity;

}

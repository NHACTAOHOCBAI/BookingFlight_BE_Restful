package com.bookingflight.demo.dto.request;

import com.bookingflight.demo.entity.Airport;
import com.bookingflight.demo.entity.Flight;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightIntermediateAirportRequest {
    Flight flight;
    Airport airport;
    Integer stopoverDuration;
    String note;
}

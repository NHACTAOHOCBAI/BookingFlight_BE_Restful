package com.bookingflight.demo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightIntermediateAirportResponse {
    String id;
    String flightId;
    String airportId;
    Integer stopoverDuration;
    String note;
}

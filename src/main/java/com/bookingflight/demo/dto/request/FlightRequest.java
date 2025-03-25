package com.bookingflight.demo.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightRequest {
    BigDecimal basePrice;
    String departureAirportId;
    String arrivalAirportId;
    Date departureTime;
    Integer flightDuration;

    List<FlightSeatClassRequest> listFlightSeatClassRequests;;
    List<FlightIntermediateAirportRequest> listFlightIntermediateAirportRequests;
}

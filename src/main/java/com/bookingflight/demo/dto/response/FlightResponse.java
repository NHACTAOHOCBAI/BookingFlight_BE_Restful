package com.bookingflight.demo.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightResponse {

    String flightCode;
    BigDecimal basePrice;
    String departureAirportId;
    String arrivalAirportId;
    Date departureTime;
    Integer flightDuration;

    List<FlightSeatClassResponse> listFlightSeatClassResponses;
    List<FlightIntermediateAirportResponse> listFlightIntermediateAirportResponses;
}

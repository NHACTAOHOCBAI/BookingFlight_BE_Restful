package com.bookingflight.demo.dto.request;

import com.bookingflight.demo.entity.Airport;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightRequest {
    BigDecimal basePrice;
    Airport departureAirport;
    Airport arrivalAirport;
    Date departureTime;
    Integer flightDuration;
}

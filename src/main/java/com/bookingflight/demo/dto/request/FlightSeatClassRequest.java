package com.bookingflight.demo.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightSeatClassRequest {
    String flightId;
    String seatClassId;
    Integer quantity;
}

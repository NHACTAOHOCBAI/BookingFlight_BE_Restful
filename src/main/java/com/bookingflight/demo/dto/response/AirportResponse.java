package com.bookingflight.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirportResponse {
    private String id;
    private String airportName;
    private String location;
}

package com.bookingflight.demo.dto.request;

import java.math.BigDecimal;

import com.bookingflight.demo.entity.Customer;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.SeatClass;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightTicketRequest {
    BigDecimal price;
    String status;
    Flight flight;
    Customer customer;
    SeatClass seatClass;
}

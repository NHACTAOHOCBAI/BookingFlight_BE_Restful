package com.bookingflight.demo.dto.response;

import com.bookingflight.demo.entity.Customer;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.SeatClass;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightTicketResponse {
    String ticketCode;
    BigDecimal price;
    String status;
    Flight flight;
    Customer customer;
    SeatClass seatClass;
}

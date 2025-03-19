package com.bookingflight.demo.mapper;

import com.bookingflight.demo.dto.request.FlightTicketRequest;
import com.bookingflight.demo.dto.response.FlightTicketResponse;
import com.bookingflight.demo.entity.FlightTicket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlightTicketMapper {
    FlightTicket toFlightTicket(FlightTicketRequest flightTicketRequest);
    FlightTicketResponse toFlightTicketResponse(FlightTicket flightTicket);
    void updateFlightTicket(@MappingTarget FlightTicketRequest flightTicketRequest, FlightTicket flightTicket);
}

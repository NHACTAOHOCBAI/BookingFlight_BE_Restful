package com.bookingflight.demo.mapper;

import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.response.FlightResponse;
import com.bookingflight.demo.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    Flight toFlight(FlightRequest flight);
    FlightResponse toFlightRespone(Flight flight);
    void updateFlight(@MappingTarget Flight flight, FlightRequest request);
}

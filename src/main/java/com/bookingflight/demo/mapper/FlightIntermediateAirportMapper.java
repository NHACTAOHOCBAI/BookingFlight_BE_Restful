package com.bookingflight.demo.mapper;

import org.mapstruct.Mapper;

import com.bookingflight.demo.dto.request.FlightIntermediateAirportRequest;
import com.bookingflight.demo.dto.response.FlightIntermediateAirportResponse;
import com.bookingflight.demo.entity.FlightIntermediateAirport;

@Mapper(componentModel = "spring")
public interface FlightIntermediateAirportMapper {
    FlightIntermediateAirport toFlightIntermediateAirport(FlightIntermediateAirportRequest request);

    FlightIntermediateAirportResponse toFlightIntermediateAirportResponse(FlightIntermediateAirport entity);

    FlightIntermediateAirport toFlightIntermediateAirport(
            FlightIntermediateAirportResponse flightIntermediateAirportResponse);
}

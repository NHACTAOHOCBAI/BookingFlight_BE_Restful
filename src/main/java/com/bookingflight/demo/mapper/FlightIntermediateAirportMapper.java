package com.bookingflight.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookingflight.demo.dto.request.FlightIntermediateAirportRequest;
import com.bookingflight.demo.dto.response.FlightIntermediateAirportResponse;
import com.bookingflight.demo.entity.Airport;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.FlightIntermediateAirport;

@Mapper(componentModel = "spring")
public interface FlightIntermediateAirportMapper {
    // @Mapping(target = "flight", expression =
    // "java(getFlight(request.getFlightId()))")
    // @Mapping(target = "airport", expression =
    // "java(getAirport(request.getAirportId()))")
    FlightIntermediateAirport toFlightIntermediateAirport(FlightIntermediateAirportRequest request);

    FlightIntermediateAirportResponse toFlightIntermediateAirportResponse(FlightIntermediateAirport entity);

    FlightIntermediateAirport toFlightIntermediateAirport(
            FlightIntermediateAirportResponse flightIntermediateAirportResponse);

    FlightIntermediateAirport toFlightIntermediateAirport(
            FlightIntermediateAirportRequest flightIntermediateAirportRequest, Flight flight, Airport airport);
}

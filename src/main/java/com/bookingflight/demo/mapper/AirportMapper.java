package com.bookingflight.demo.mapper;

import com.bookingflight.demo.dto.request.AirportRequest;
import com.bookingflight.demo.dto.response.AirportResponse;
import com.bookingflight.demo.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport toAirport(AirportRequest airportRequest);
    AirportResponse toAirportResponse(Airport airport);
    void updateAirport(@MappingTarget Airport airport, AirportRequest request);
}

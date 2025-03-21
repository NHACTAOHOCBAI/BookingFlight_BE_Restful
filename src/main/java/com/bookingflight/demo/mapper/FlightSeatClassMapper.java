package com.bookingflight.demo.mapper;

import org.mapstruct.Mapper;

import com.bookingflight.demo.dto.request.FlightSeatClassRequest;
import com.bookingflight.demo.dto.response.FlightSeatClassResponse;
import com.bookingflight.demo.entity.FlightSeatClass;

@Mapper(componentModel = "spring")
public interface FlightSeatClassMapper {
    FlightSeatClass toFlightSeatClass(FlightSeatClassRequest request);

    FlightSeatClassResponse toFlightSeatClassResponse(FlightSeatClass entity);

    FlightSeatClass toFlightSeatClass(FlightSeatClassResponse response);
}

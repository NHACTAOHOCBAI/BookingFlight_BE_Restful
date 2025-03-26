package com.bookingflight.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.bookingflight.demo.dto.request.FlightSeatClassRequest;
import com.bookingflight.demo.dto.response.FlightSeatClassResponse;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.FlightSeatClass;
import com.bookingflight.demo.entity.SeatClass;

@Mapper(componentModel = "spring")
public interface FlightSeatClassMapper {

        FlightSeatClass toFlightSeatClass(FlightSeatClassRequest request);

        FlightSeatClassResponse toFlightSeatClassResponse(FlightSeatClass entity);

}

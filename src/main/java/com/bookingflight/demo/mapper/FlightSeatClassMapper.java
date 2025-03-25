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
        // @Mapping(target = "flight", expression =
        // "java(getFlight(request.getFlightId()))")
        // @Mapping(target = "seatClass", expression =
        // "java(getSeatClass(request.getSeatClassId()))")
        FlightSeatClass toFlightSeatClass(FlightSeatClassRequest request);

        FlightSeatClassResponse toFlightSeatClassResponse(FlightSeatClass entity);

}

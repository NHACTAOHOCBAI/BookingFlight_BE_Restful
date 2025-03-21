package com.bookingflight.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.bookingflight.demo.dto.request.SeatClassRequest;
import com.bookingflight.demo.dto.response.SeatClassResponse;
import com.bookingflight.demo.entity.SeatClass;

@Mapper(componentModel = "spring")
public interface SeatClassMapper {
    SeatClass toEntity(SeatClassRequest request);

    SeatClassResponse toResponse(SeatClass entity);

    static void updateSeatClass(@MappingTarget SeatClass seatClass, SeatClassRequest request) {

    }

    SeatClass toEntity(SeatClassResponse seatClassResponse);

}

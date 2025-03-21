package com.bookingflight.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.response.FlightSeatClassResponse;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.FlightSeatClass;
import com.bookingflight.demo.entity.SeatClass;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.FlightMapper;
import com.bookingflight.demo.mapper.FlightSeatClassMapper;
import com.bookingflight.demo.repository.FlightSeatClassRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightSeatClassService {
    FlightSeatClassRepository flightSeatClassRepository;
    FlightSeatClassMapper flightSeatClassMapper;

    public FlightSeatClass createFlightSeatClass(FlightSeatClassResponse flightSeatClassResponse) {
        FlightSeatClass flightSeatClass = flightSeatClassMapper.toFlightSeatClass(flightSeatClassResponse);
        flightSeatClassRepository.save(flightSeatClass);
        return flightSeatClass;
    }

}

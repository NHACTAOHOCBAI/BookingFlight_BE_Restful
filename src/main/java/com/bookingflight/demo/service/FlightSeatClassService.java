package com.bookingflight.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.request.FlightSeatClassRequest;
import com.bookingflight.demo.dto.response.FlightIntermediateAirportResponse;
import com.bookingflight.demo.dto.response.FlightSeatClassResponse;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.FlightSeatClass;
import com.bookingflight.demo.entity.SeatClass;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.FlightMapper;
import com.bookingflight.demo.mapper.FlightSeatClassMapper;
import com.bookingflight.demo.repository.FlightRepository;
import com.bookingflight.demo.repository.FlightSeatClassRepository;
import com.bookingflight.demo.repository.SeatClassRepository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightSeatClassService {
        FlightSeatClassRepository flightSeatClassRepository;
        FlightSeatClassMapper flightSeatClassMapper;
        FlightRepository flightRepository;
        SeatClassRepository seatClassRepository;

        @Transactional
        public FlightSeatClassResponse createFlightSeatClass(FlightSeatClassRequest flightSeatClassRequest) {
                if (flightSeatClassRequest == null ||
                                flightSeatClassRequest.getFlightId() == null ||
                                flightSeatClassRequest.getSeatClassId() == null) {
                        throw new AppException(ErrorCode.INVALID_REQUEST);
                }
                // System.out.println("Here-----------------------------");

                // lấy flight từ id
                Flight flight = flightRepository.findById(flightSeatClassRequest.getFlightId())
                                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
                // lấy seatClass từ id
                SeatClass seatClass = seatClassRepository.findById(flightSeatClassRequest.getSeatClassId())
                                .orElseThrow(() -> new AppException(ErrorCode.SEATCLASS_NOT_EXISTED));

                FlightSeatClass flightSeatClass = new FlightSeatClass();
                flightSeatClass.setFlight(flight);
                flightSeatClass.setSeatClass(seatClass);
                flightSeatClass.setQuantity(flightSeatClassRequest.getQuantity());

                return flightSeatClassMapper.toFlightSeatClassResponse(flightSeatClassRepository.save(flightSeatClass));
                // flightSeatClassRepository.save(flightSeatClass);
                // System.out.println(flightSeatClass);
                // return flightSeatClassMapper.toFlightSeatClassResponse(flightSeatClass);
        }

        public List<FlightSeatClassResponse> getFlightSeatClassesByFlight(Flight flight) {
                return flightSeatClassRepository.findByFlight(flight)
                                .stream()
                                .map((flightSeatClass) -> {
                                        FlightSeatClassResponse flightSeatClassResponse = flightSeatClassMapper
                                                        .toFlightSeatClassResponse(flightSeatClass);
                                        flightSeatClassResponse
                                                        .setFlightId(flightSeatClass.getFlight().getFlightCode());
                                        flightSeatClassResponse.setSeatClassId(flightSeatClass.getSeatClass().getId());
                                        return flightSeatClassResponse;
                                })
                                .collect(Collectors.toList());
        }

}

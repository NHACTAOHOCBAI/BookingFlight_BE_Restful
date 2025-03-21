package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.request.SeatClassRequest;
import com.bookingflight.demo.dto.response.FlightIntermediateAirportResponse;
import com.bookingflight.demo.dto.response.FlightResponse;
import com.bookingflight.demo.dto.response.FlightSeatClassResponse;
import com.bookingflight.demo.dto.response.SeatClassResponse;
import com.bookingflight.demo.entity.Airport;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.FlightIntermediateAirport;
import com.bookingflight.demo.entity.FlightSeatClass;
import com.bookingflight.demo.entity.SeatClass;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.FlightMapper;
import com.bookingflight.demo.mapper.SeatClassMapper;
import com.bookingflight.demo.repository.AirportRepository;
import com.bookingflight.demo.repository.FlightIntermediateAirportRepository;
import com.bookingflight.demo.repository.FlightRepository;
import com.bookingflight.demo.repository.FlightSeatClassRepository;
import com.bookingflight.demo.repository.SeatClassRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightService {
    FlightRepository flightRepository;
    AirportRepository airportRepository;
    FlightSeatClassService flightSeatClassService;
    FlightIntermediateAirportService flightIntermediateAirportSeatClassServices;
    FlightMapper flightMapper;

    public FlightResponse createFlight(FlightRequest request) {
        // Validate departure airport exists
        Airport departureAirport = airportRepository.findById(request.getDepartureAirport().getAirportCode())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));

        // Validate arrival airport exists
        Airport arrivalAirport = airportRepository.findById(request.getArrivalAirport().getAirportCode())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));

        // Create flight entity
        Flight flight = flightMapper.toFlight(request);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setBasePrice(request.getBasePrice());

        for (FlightSeatClassResponse flightSeatClassResponse : request.getFlightSeatClassResponses()) {
            flightSeatClassResponse.setFlight(flight);
            flightSeatClassService.createFlightSeatClass(flightSeatClassResponse);
        }
        for (FlightIntermediateAirportResponse flightIntermediateAirportResponse : request
                .getFlightIntermediateAirportResponses()) {
            flightIntermediateAirportResponse.setFlight(flight);
            flightIntermediateAirportSeatClassServices
                    .createFlightIntermediateAirport(flightIntermediateAirportResponse);
        }
        return flightMapper.toFlightRespone(flightRepository.save(flight));
    }

}

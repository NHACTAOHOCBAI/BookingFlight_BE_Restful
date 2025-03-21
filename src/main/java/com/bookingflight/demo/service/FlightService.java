package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.response.FlightIntermediateAirportResponse;
import com.bookingflight.demo.dto.response.FlightResponse;
import com.bookingflight.demo.dto.response.FlightSeatClassResponse;
import com.bookingflight.demo.entity.Airport;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.FlightMapper;
import com.bookingflight.demo.repository.AirportRepository;
import com.bookingflight.demo.repository.FlightRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

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
        Airport departureAirport = airportRepository
                .getAirportByAirportCode(request.getDepartureAirport().getAirportCode());

        // Validate arrival airport exists
        Airport arrivalAirport = airportRepository
                .getAirportByAirportCode(request.getArrivalAirport().getAirportCode());

        if (arrivalAirport == null || departureAirport == null)
            throw new AppException(ErrorCode.AIRPORT_NOT_EXISTED);

        System.out.println(request);
        // Create flight entity
        Flight flight = new Flight();
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setBasePrice(request.getBasePrice());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setFlightDuration(request.getFlightDuration());
        flightRepository.save(flight);

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
        return flightMapper.toFlightRespone(flight);
    }

}

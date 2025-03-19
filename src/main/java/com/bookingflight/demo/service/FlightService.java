package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.response.FlightResponse;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightService {
    FlightRepository flightRepository;
    AirportRepository airportRepository;
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

        return flightMapper.toFlightRespone(flightRepository.save(flight));
    }
    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(flightMapper::toFlightRespone).collect(Collectors.toList());
    }

    public FlightResponse getFlight(String flightCode) {
        Flight flight = flightRepository.findById(flightCode)
                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
        return flightMapper.toFlightRespone(flight);
    }

    public Void deleteFlight(String flightCode) {
        flightRepository.findById(flightCode)
                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
        flightRepository.deleteById(flightCode);
        return null;
    }

    public FlightResponse updateFlight(String flightCode, FlightRequest request) {
        Flight flight = flightRepository.findById(flightCode)
                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
        flightMapper.updateFlight(flight, request);
        return flightMapper.toFlightRespone(flightRepository.save(flight));
    }
}

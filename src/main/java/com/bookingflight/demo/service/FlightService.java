package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.FlightIntermediateAirportRequest;
import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.request.FlightSeatClassRequest;
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

import java.util.List;

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
                                .findById(request.getDepartureAirportId())
                                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));
                // Validate arrival airport exists
                Airport arrivalAirport = airportRepository
                                .findById(request.getArrivalAirportId())
                                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));
                // Create flight entity
                Flight flight = new Flight();
                flight.setArrivalAirport(arrivalAirport);
                flight.setDepartureAirport(departureAirport);
                flight.setBasePrice(request.getBasePrice());
                flight.setDepartureTime(request.getDepartureTime());
                flight.setFlightDuration(request.getFlightDuration());
                // Save flight to DB
                flightRepository.save(flight);

                for (FlightSeatClassRequest flightSeatClassRequest : request.getListFlightSeatClassRequests()) {
                        flightSeatClassRequest.setFlightId(flight.getFlightCode());
                        flightSeatClassService.createFlightSeatClass(flightSeatClassRequest);
                }
                for (FlightIntermediateAirportRequest flightIntermediateAirportRequest : request
                                .getListFlightIntermediateAirportRequests()) {
                        flightIntermediateAirportRequest.setFlightId(flight.getFlightCode());
                        flightIntermediateAirportSeatClassServices
                                        .createFlightIntermediateAirport(flightIntermediateAirportRequest);
                }

                FlightResponse flightResponse = flightMapper.toFlightRespone(flight);
                List<FlightSeatClassResponse> listFlightSeatClassResponses = flightSeatClassService
                                .getFlightSeatClassesByFlight(flight);

                flightResponse.setListFlightSeatClassResponses(listFlightSeatClassResponses);
                List<FlightIntermediateAirportResponse> flightIntermediateAirports = flightIntermediateAirportSeatClassServices
                                .getFlightIntermediateAirportsByFlight(flight);
                flightResponse.setListFlightIntermediateAirportResponses(flightIntermediateAirports);

                return flightResponse;
        }

}

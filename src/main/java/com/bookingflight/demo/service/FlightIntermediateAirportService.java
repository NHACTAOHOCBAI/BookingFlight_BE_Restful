package com.bookingflight.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookingflight.demo.dto.request.FlightIntermediateAirportRequest;
import com.bookingflight.demo.dto.response.FlightIntermediateAirportResponse;
import com.bookingflight.demo.entity.Airport;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.FlightIntermediateAirport;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.FlightIntermediateAirportMapper;
import com.bookingflight.demo.repository.AirportRepository;
import com.bookingflight.demo.repository.FlightIntermediateAirportRepository;
import com.bookingflight.demo.repository.FlightRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightIntermediateAirportService {
        private final FlightIntermediateAirportRepository repository;
        private final FlightIntermediateAirportMapper flightIntermediateAirportMapper;
        private final FlightRepository flightRepository;
        private final AirportRepository airportRepository;

        public FlightIntermediateAirportResponse createFlightIntermediateAirport(
                        FlightIntermediateAirportRequest flightIntermediateAirportRequest) {
                // lấy flight từ Id
                Flight flight = flightRepository.findById(flightIntermediateAirportRequest.getFlightId())
                                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
                // lấy airport từ id
                Airport airport = airportRepository.findById(flightIntermediateAirportRequest.getAirportId())
                                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));

                FlightIntermediateAirport flightIntermediateAirport = new FlightIntermediateAirport();
                flightIntermediateAirport.setFlight(flight);
                flightIntermediateAirport.setAirport(airport);
                flightIntermediateAirport.setStopoverDuration(flightIntermediateAirportRequest.getStopoverDuration());
                flightIntermediateAirport.setNote(flightIntermediateAirportRequest.getNote());
                return flightIntermediateAirportMapper
                                .toFlightIntermediateAirportResponse(repository.save(flightIntermediateAirport));
        }

        public List<FlightIntermediateAirportResponse> getFlightIntermediateAirportsByFlight(Flight flight) {
                return repository.findByFlight(flight)
                                .stream()
                                .map(flightIntermediateAirport -> {
                                        FlightIntermediateAirportResponse response = flightIntermediateAirportMapper
                                                        .toFlightIntermediateAirportResponse(flightIntermediateAirport);
                                        response.setFlightId(flightIntermediateAirport.getFlight().getFlightCode());
                                        response.setAirportId(flightIntermediateAirport.getAirport().getId());
                                        return response;
                                })
                                .collect(Collectors.toList());
        }

}

package com.bookingflight.demo.service;

import org.springframework.stereotype.Service;

import com.bookingflight.demo.dto.response.FlightIntermediateAirportResponse;
import com.bookingflight.demo.entity.FlightIntermediateAirport;
import com.bookingflight.demo.mapper.FlightIntermediateAirportMapper;
import com.bookingflight.demo.repository.FlightIntermediateAirportRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightIntermediateAirportService {
    private final FlightIntermediateAirportRepository repository;
    private final FlightIntermediateAirportMapper flightIntermediateAirportMapper;

    public void createFlightIntermediateAirport(FlightIntermediateAirportResponse flightIntermediateAirportResponse) {
        FlightIntermediateAirport flightIntermediateAirport = flightIntermediateAirportMapper
                .toFlightIntermediateAirport(flightIntermediateAirportResponse);
        repository.save(flightIntermediateAirport);
    }

}

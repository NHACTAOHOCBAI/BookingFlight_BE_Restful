package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.AirportRequest;
import com.bookingflight.demo.dto.response.AirportResponse;
import com.bookingflight.demo.entity.Airport;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.AirportMapper;
import com.bookingflight.demo.repository.AirportRespository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirportService {
    AirportRespository airportRespository;
    private final AirportMapper airportMapper;

    public AirportResponse createAirport(AirportRequest request) {
        if (airportRespository.existsByAirportName(request.getAirportName()))
            throw new AppException(ErrorCode.AIRPORT_EXISTED);
        Airport airport = airportMapper.toAirport(request);
        return airportMapper.toAirportResponse(airportRespository.save(airport));
    }

    public List<AirportResponse> getAllAirports() {
        List<Airport> airports = airportRespository.findAll();
        return airports.stream().map(airportMapper::toAirportResponse).collect(Collectors.toList());
    }

    public AirportResponse getAirport(String airportId) {
        Airport airport = airportRespository.findById(airportId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return airportMapper.toAirportResponse(airport);
    }

    public Void deleteAirport(String airportId) {
        airportRespository.findById(airportId).orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED ));
        airportRespository.deleteById(airportId);
        return null;
    }

    public AirportResponse updateAirport(String airportId, AirportRequest request) {
        Airport airport= airportRespository.findById(airportId).orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED ));
        airportMapper.updateAirport(airport,request);
        return airportMapper.toAirportResponse(airportRespository.save(airport));
    }
}

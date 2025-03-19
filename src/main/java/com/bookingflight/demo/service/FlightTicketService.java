package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.FlightTicketRequest;
import com.bookingflight.demo.dto.response.FlightTicketResponse;
import com.bookingflight.demo.entity.Customer;
import com.bookingflight.demo.entity.Flight;
import com.bookingflight.demo.entity.FlightTicket;
import com.bookingflight.demo.entity.SeatClass;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.FlightTicketMapper;
import com.bookingflight.demo.repository.CustomerRepository;
import com.bookingflight.demo.repository.FlightRepository;
import com.bookingflight.demo.repository.FlightTicketRepository;
import com.bookingflight.demo.repository.SeatClassRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightTicketService {
    FlightTicketRepository flightTicketRepository;
    FlightTicketMapper flightTicketMapper;
    FlightRepository flightRepository;
    CustomerRepository customerRepository;
    SeatClassRepository seatClassRepository;
    public FlightTicketResponse createFlightTicket(FlightTicketRequest request) {
        Flight flight = flightRepository.findById(request.getFlight().getFlightCode())
                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
        Customer customer = customerRepository.findById(request.getCustomer().getId())
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        SeatClass seatClass = seatClassRepository.findById(request.getSeatClass().getId())
                .orElseThrow(() -> new AppException(ErrorCode.SEATCLASS_NOT_EXISTED));

        BigDecimal basePrice = flight.getBasePrice();
        BigDecimal priceMultiplier = BigDecimal.valueOf(seatClass.getPriceMultiplier());
        BigDecimal price = basePrice.multiply(priceMultiplier);
        FlightTicket flightTicket = flightTicketMapper.toFlightTicket(request);
        flightTicket.setCustomer(customer);
        flightTicket.setSeatClass(seatClass);
        flightTicket.setFlight(flight);
        flightTicket.setPrice(price);
        return flightTicketMapper.toFlightTicketResponse(flightTicketRepository.save(flightTicket));
    }

    public List<FlightTicketResponse> getAllFlightTickets() {
        List<FlightTicket> flightTickets = flightTicketRepository.findAll();
        return flightTickets.stream().map(flightTicketMapper::toFlightTicketResponse).collect(Collectors.toList());
    }

    public FlightTicketResponse getFlightTicketById(String ticketId) {
        FlightTicket flightTicket = flightTicketRepository.findById(ticketId)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_EXISTED));
        return flightTicketMapper.toFlightTicketResponse(flightTicket);
    }

    public Void deleteFlightTicket(String ticketId) {
        flightTicketRepository.findById(ticketId)
                        .orElseThrow(()-> new AppException(ErrorCode.TICKET_NOT_EXISTED));
        flightTicketRepository.deleteById(ticketId);
        return null;
    }

    public FlightTicketResponse updateFlightTicket(String ticketId, FlightTicketRequest request) {
        FlightTicket flightTicket = flightTicketRepository.findById(ticketId)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_EXISTED));
        flightTicketMapper.updateFlightTicket(request, flightTicket);
        return flightTicketMapper.toFlightTicketResponse(flightTicket);
    }
}

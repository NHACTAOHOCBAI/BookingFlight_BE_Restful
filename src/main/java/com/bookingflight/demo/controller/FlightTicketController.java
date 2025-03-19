package com.bookingflight.demo.controller;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.FlightTicketRequest;
import com.bookingflight.demo.dto.response.FlightTicketResponse;
import com.bookingflight.demo.service.FlightTicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightTicketController {
    FlightTicketService flightTicketService;

    //create
    @PostMapping()
    ResponseEntity<APIResponse<FlightTicketResponse>> createFlightTicket(@RequestBody FlightTicketRequest request) {
        APIResponse<FlightTicketResponse> apiResponse = APIResponse.<FlightTicketResponse>builder()
                .code(201)
                .message("Flight ticket created")
                .result(flightTicketService.createFlightTicket(request))
                .build();
        return ResponseEntity.created(URI.create("/tickets")).body(apiResponse);
    }

    //get all
    @GetMapping()
    ResponseEntity<APIResponse<List<FlightTicketResponse>>> getAllFlightTickets() {
        APIResponse<List<FlightTicketResponse>> apiResponse = APIResponse.<List<FlightTicketResponse>>builder()
                .code(200)
                .message("Get all ticket")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    // get one
    @GetMapping("/{ticketCode}")
    ResponseEntity<APIResponse<FlightTicketResponse>> getFlightTicket(@PathVariable("ticketCode") String ticketCode) {
        APIResponse<FlightTicketResponse> apiResponse = APIResponse.<FlightTicketResponse>builder()
                .code(200)
                .message("Get a flight")
                .result(flightTicketService.getFlightTicketById(ticketCode))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    //delete
    @DeleteMapping("/{ticketCode}")
    ResponseEntity<APIResponse<Void>> deleteFlightTicket(@PathVariable("ticketCode") String ticketCode) {
        flightTicketService.deleteFlightTicket(ticketCode);
        APIResponse<Void> apiResponse = APIResponse.<Void>builder()
                .code(204)
                .message("Ticket deleted")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    //update
    @PutMapping("/{ticketcode}")
    ResponseEntity<APIResponse<FlightTicketResponse>> updateFlight(@PathVariable("ticketCode") String ticketCode, @RequestBody FlightTicketRequest request) {
        APIResponse<FlightTicketResponse> apiResponse = APIResponse.<FlightTicketResponse>builder()
                .code(200)
                .message("Ticket updated")
                .result(flightTicketService.updateFlightTicket(ticketCode, request))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}

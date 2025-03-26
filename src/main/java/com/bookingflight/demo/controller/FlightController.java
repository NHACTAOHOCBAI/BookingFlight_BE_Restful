package com.bookingflight.demo.controller;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.FlightRequest;
import com.bookingflight.demo.dto.response.FlightResponse;
import com.bookingflight.demo.service.FlightService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightController {
    FlightService flightService;

    // create
    @PostMapping()
    ResponseEntity<APIResponse<FlightResponse>> createFlight(@RequestBody FlightRequest request) {
        APIResponse<FlightResponse> apiResponse = APIResponse.<FlightResponse>builder()
                .code(201)
                .message("Flight created")
                .result(flightService.createFlight(request))
                .build();
        return ResponseEntity.created(URI.create("/flights")).body(apiResponse);
    }

    @GetMapping()
    ResponseEntity<APIResponse<List<FlightResponse>>> getAllFlights() {
        APIResponse<List<FlightResponse>> apiResponse = APIResponse.<List<FlightResponse>>builder()
                .code(200)
                .message("Get all flights")
                .result(flightService.getAllFlights())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
    // get all
    // @GetMapping()
    // ResponseEntity<APIResponse<List<FlightResponse>>> getAllFlights() {
    // APIResponse<List<FlightResponse>> apiResponse =
    // APIResponse.<List<FlightResponse>>builder()
    // .code(200)
    // .message("Get all flights")
    // .result(flightService.getAllFlights())
    // .build();
    // return ResponseEntity.ok().body(apiResponse);
    // }

    // get one
    @GetMapping("/{flightCode}")
    ResponseEntity<APIResponse<FlightResponse>> getFlight(@PathVariable("flightCode") String flightCode) {
        APIResponse<FlightResponse> apiResponse = APIResponse.<FlightResponse>builder()
                .code(200)
                .message("Get a flight")
                .result(flightService.getFlightById(flightCode))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    // //delete
    // @DeleteMapping("/{flightCode}")
    // ResponseEntity<APIResponse<Void>> deleteFlight(@PathVariable("flightCode")
    // String flightCode) {
    // flightService.deleteFlight(flightCode);
    // APIResponse<Void> apiResponse = APIResponse.<Void>builder()
    // .code(204)
    // .message("Flight deleted")
    // .build();
    // return ResponseEntity.ok().body(apiResponse);
    // }

    // //update
    // @PutMapping("/{flightCode}")
    // ResponseEntity<APIResponse<FlightResponse>>
    // updateFlight(@PathVariable("flightCode") String flightCode, @RequestBody
    // FlightRequest request) {
    // APIResponse<FlightResponse> apiResponse =
    // APIResponse.<FlightResponse>builder()
    // .code(200)
    // .message("Flight updated")
    // .result(flightService.updateFlight(flightCode, request))
    // .build();
    // return ResponseEntity.ok().body(apiResponse);
    // }
}

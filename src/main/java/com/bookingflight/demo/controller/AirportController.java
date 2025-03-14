package com.bookingflight.demo.controller;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.AirportRequest;
import com.bookingflight.demo.dto.response.AirportResponse;
import com.bookingflight.demo.service.AirportService;
import com.bookingflight.demo.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirportController {

    AirportService airportService;
    private final UserService userService;

    // create
    @PostMapping()
        ResponseEntity<APIResponse<AirportResponse>> createAirport(@RequestBody AirportRequest request) {
        APIResponse<AirportResponse> apiResponse=APIResponse.<AirportResponse>builder()
                    .code(201)
                    .message("Airport created")
                    .result(airportService.createAirport(request))
                .build();
        return ResponseEntity.created(URI.create("/airports")).body(apiResponse);
    }

    // get all
    @GetMapping()
    ResponseEntity<APIResponse<List<AirportResponse>>> getAllAirports() {
        APIResponse<List<AirportResponse>> apiResponse=APIResponse.<List<AirportResponse>>builder()
                .code(200)
                .message("Get all airports")
                .result(airportService.getAllAirports())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    //get one
    @GetMapping("/{airportId}")
    ResponseEntity<APIResponse<AirportResponse>> getAirport(@PathVariable("airportId") String airportId) {
        APIResponse<AirportResponse> apiResponse=APIResponse.<AirportResponse>builder()
                .code(200)
                .message("Get a airport")
                .result(airportService.getAirport(airportId))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    //delete
    @DeleteMapping("/{airportId}")
    ResponseEntity<APIResponse<Void>> deleteAirport(@PathVariable("airportId") String airportId) {
        airportService.deleteAirport(airportId);
        APIResponse<Void> apiResponse = APIResponse.<Void>builder()
                .code(204)
                .message("Airport deleted")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    //update
    @PutMapping("/{airportId}")
    ResponseEntity<APIResponse<AirportResponse>>  updateAirport(@PathVariable("airportId") String airportId,@RequestBody AirportRequest airportRequest ) {
        APIResponse<AirportResponse> apiResponse = APIResponse.<AirportResponse>builder()
                .code(200)
                .message("Airport updated")
                .result(airportService.updateAirport(airportId,airportRequest))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}

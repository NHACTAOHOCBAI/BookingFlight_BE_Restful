package com.bookingflight.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.SeatClassRequest;
import com.bookingflight.demo.dto.response.SeatClassResponse;
import com.bookingflight.demo.service.SeatClassService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/seatclasses")
@RequiredArgsConstructor

public class SeatClassController {

    private final SeatClassService seatClassService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<SeatClassResponse>> getSeatClassByID(@PathVariable("id") String id) {
        APIResponse<SeatClassResponse> apiResponse = APIResponse.<SeatClassResponse>builder()
                .code(200)
                .message("Get seat class by ID")
                .result(seatClassService.getSeatClassByID(id))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<APIResponse<List<SeatClassResponse>>> getAllSeatClass() {
        APIResponse<List<SeatClassResponse>> apiResponse = APIResponse.<List<SeatClassResponse>>builder()
                .code(200)
                .message("Get all seat classes")
                .result(seatClassService.getAllSeatClasses())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<SeatClassResponse>> updateSeatClass(@PathVariable String id,
            @RequestBody SeatClassRequest entity) {
        APIResponse<SeatClassResponse> apiResponse = APIResponse.<SeatClassResponse>builder()
                .code(200)
                .message("Update seat class by ID")
                .result(seatClassService.updateSeatClass(id, entity))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<APIResponse<SeatClassResponse>> createSeatClass(@RequestBody SeatClassRequest entity) {
        APIResponse<SeatClassResponse> apiResponse = APIResponse.<SeatClassResponse>builder()
                .code(201)
                .message("Create seat class")
                .result(seatClassService.createSeatClass(entity))
                .build();
        return ResponseEntity.created(null).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteSeatClass(@PathVariable String id) {
        seatClassService.deleteSeatClass(id);
        APIResponse<Void> apiResponse = APIResponse.<Void>builder()
                .code(204)
                .message("Delete seat class by ID")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
package com.bookingflight.demo.controller;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.CustomerRequest;
import com.bookingflight.demo.dto.response.CustomerResponse;
import com.bookingflight.demo.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    //create
    @PostMapping
        ResponseEntity<APIResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest request) {
        APIResponse<CustomerResponse> apiResponse = APIResponse.<CustomerResponse>builder()
                    .code(201)
                    .message("Customer created")
                    .result(customerService.createCustomer(request))
                .build();
        return ResponseEntity.created(URI.create("/customers")).body(apiResponse);
    }

    //get all
    @GetMapping()
    ResponseEntity<APIResponse<List<CustomerResponse>>> getAllCustomers() {
        APIResponse<List<CustomerResponse>> apiResponse = APIResponse.<List<CustomerResponse>>builder()
                .code(200)
                .message("Get all customer")
                .result(customerService.getAllCustomers())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    //get one
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<CustomerResponse>> getCustomer(@PathVariable("id") String id) {
        APIResponse<CustomerResponse> apiResponse = APIResponse.<CustomerResponse>builder()
                .code(200)
                .message("Get a customer")
                .result(customerService.getCustomer(id))
                .build();
        return  ResponseEntity.ok().body(apiResponse);
    }

    //delete
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<Void>> deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        APIResponse<Void> apiResponse = APIResponse.<Void>builder()
                .code(204)
                .message("Customer delete")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    //update
    @PutMapping("/{id}")
    ResponseEntity<APIResponse<CustomerResponse>> updateCustomer(@PathVariable("id") String id, @RequestBody CustomerRequest request) {
        APIResponse<CustomerResponse> apiResponse = APIResponse.<CustomerResponse>builder()
                .code(200)
                .message(("Customer updated"))
                .result(customerService.updateCustomer(id, request))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}

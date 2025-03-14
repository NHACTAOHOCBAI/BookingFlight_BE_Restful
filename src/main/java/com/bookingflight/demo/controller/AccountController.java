package com.bookingflight.demo.controller;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.AccountRequest;
import com.bookingflight.demo.dto.response.AccountResponse;
import com.bookingflight.demo.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    // create
    @PostMapping()
    ResponseEntity<APIResponse<AccountResponse>> createAccount(@RequestBody AccountRequest request) {
        APIResponse<AccountResponse> apiResponse = APIResponse.<AccountResponse>builder()
                .code(201)
                .message("Account created")
                .result(accountService.createAccount(request))
                .build();
        return ResponseEntity.created(URI.create("/accounts")).body(apiResponse);
    }

    // get all
    @GetMapping()
    ResponseEntity<APIResponse<List<AccountResponse>>> getAllAccounts() {
        APIResponse<List<AccountResponse>> apiResponse = APIResponse.<List<AccountResponse>>builder()
                .code(200)
                .message("Get all accounts")
                .result(accountService.getAllAccounts())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    // get one
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<AccountResponse>> getAccount(@PathVariable("id") String id) {
        APIResponse<AccountResponse> apiResponse = APIResponse.<AccountResponse>builder()
                .code(200)
                .message("Get an account")
                .result(accountService.getAccount(id))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    // delete
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<Void>> deleteAccount(@PathVariable("id") String id) {
        accountService.deleteAccount(id);
        APIResponse<Void> apiResponse = APIResponse.<Void>builder()
                .code(204)
                .message("Account deleted")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    // update
    @PutMapping("/{id}")
    ResponseEntity<APIResponse<AccountResponse>> updateAccount(@PathVariable("id") String id,
            @RequestBody AccountRequest request) {
        APIResponse<AccountResponse> apiResponse = APIResponse.<AccountResponse>builder()
                .code(200)
                .message("Account updated")
                .result(accountService.updateAccount(id, request))
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}

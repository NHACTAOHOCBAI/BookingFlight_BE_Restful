package com.bookingflight.demo.controller;

import java.util.List;

import com.bookingflight.demo.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingflight.demo.dto.request.APIResponse;
import com.bookingflight.demo.dto.request.UserCreationRequest;
import com.bookingflight.demo.dto.request.UserUpdationRequest;
import com.bookingflight.demo.entity.User;
import com.bookingflight.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        APIResponse<User> apiResponse = new APIResponse<User>();
        apiResponse.setResult(userService.createRequest(request));
        apiResponse.setCode(100);
        apiResponse.setMessage("create successfully");
        return apiResponse;
    }

    @GetMapping()
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdationRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }
}

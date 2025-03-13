package com.bookingflight.demo.service;

import java.util.List;

import com.bookingflight.demo.dto.response.UserResponse;
import com.bookingflight.demo.enums.Role;
import com.bookingflight.demo.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookingflight.demo.dto.request.UserCreationRequest;
import com.bookingflight.demo.dto.request.UserUpdationRequest;
import com.bookingflight.demo.entity.User;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
// tao constructor cho nhung thuoc tinh final,no null
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
// make final=> nhung thuoc tinh khong khai bao se thanh final private
//=> Tao constructor , dependency injection
public class UserService {
     UserRepository userRepository;
     UserMapper userMapper;
    public User createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
       User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        // tao encoder bcrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER.name());
        // name():tra ve ten cua hang so
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
        // tim user sau do convert sang UserResponse
    }

    public UserResponse updateUser(String userId, UserUpdationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        // convert data tu request sang user
        return userMapper.toUserResponse(userRepository.save(user));
        // goi update sau do convert sang UserResponse
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "deleted the user";
    }
}

package com.bookingflight.demo.mapper;

import com.bookingflight.demo.dto.request.UserCreationRequest;
import com.bookingflight.demo.dto.request.UserUpdationRequest;
import com.bookingflight.demo.dto.response.UserResponse;
import com.bookingflight.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
// bien mapper nay thanh bean de cho spring quan ly=> Dependency enjection
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    // Chuyen doi tu UserCreationRequest sang mot User moi.
    void updateUser(@MappingTarget User user, UserUpdationRequest request);
    // Cap nhat du lieu tren mot object User co san thay vi tao moi.
}

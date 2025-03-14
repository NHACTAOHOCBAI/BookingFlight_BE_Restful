package com.bookingflight.demo.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequest {
    Integer roleId;
    String userName;
    String password;
    String avatar;
    String fullName;
    String phoneNumber;
    String dateOfBirth;
    String address;
}

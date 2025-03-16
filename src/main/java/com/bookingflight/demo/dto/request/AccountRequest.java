package com.bookingflight.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
    @Email(regexp = "^[A-Za-z0-9+_.-]+@gmail\\.com$",message = "EMAIL_INVALID")
    String userName;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String avatar;
    String fullName;
    String phoneNumber;
    String dateOfBirth;
    String address;
}

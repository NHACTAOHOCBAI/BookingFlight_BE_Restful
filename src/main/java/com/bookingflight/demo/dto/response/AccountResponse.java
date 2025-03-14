package com.bookingflight.demo.dto.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
     String id;
     Integer roleId;
     String userName;
     String password;
     String avatar;
     String fullName;
     String phoneNumber;
     String dateOfBirth;
     String address;
}

package com.bookingflight.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdationRequest {
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}

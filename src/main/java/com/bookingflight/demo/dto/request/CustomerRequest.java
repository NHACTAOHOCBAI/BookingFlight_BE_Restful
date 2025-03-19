package com.bookingflight.demo.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    String fullName;
    Boolean isOver14YearsOld; // TRUE if over 14 years old, FALSE if under 14 years old
    String idCard;
    String phoneNumber;
    String email;
}

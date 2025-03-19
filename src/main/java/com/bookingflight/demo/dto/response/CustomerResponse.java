package com.bookingflight.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    String id;
    String fullName;
    Boolean isOver14YearsOld; // TRUE if over 14 years old, FALSE if under 14 years old
    String idCard;
    String phoneNumber;
    String email;
}

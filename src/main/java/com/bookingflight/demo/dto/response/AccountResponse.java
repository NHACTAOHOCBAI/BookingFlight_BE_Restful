package com.bookingflight.demo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    private String id;
    private Integer roleId;
    private String userName;
    private String password;
}

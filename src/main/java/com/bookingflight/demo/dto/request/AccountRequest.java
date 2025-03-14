package com.bookingflight.demo.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequest {
    private Integer roleId;
    private String userName;
    private String password;
}

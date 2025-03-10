package com.bookingflight.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
// khi chuyen sang json thi thuoc tinh nao null thi khong hien ra
public class APIResponse<T> {
    int code;
    String message;
    T result;
}

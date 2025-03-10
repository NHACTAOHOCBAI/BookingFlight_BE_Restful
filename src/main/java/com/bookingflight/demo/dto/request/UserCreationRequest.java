package com.bookingflight.demo.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
// cung cap @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
// giup khoi tao gia tri nhanh hon(chi dung luc khoi tao, khong update duoc)
@FieldDefaults(level = AccessLevel.PRIVATE)
// giup gan pham vi mac dinh
public class UserCreationRequest {
    String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}

package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @NotNull
    Integer roleId; // 1: Admin, 2: employee, 3: customer

    @NotNull
    String userName;

    @NotNull
    String password;

    @NotNull
    String avatar;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @NotNull
    String address;
}
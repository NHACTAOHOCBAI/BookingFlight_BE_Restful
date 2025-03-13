package com.bookingflight.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Setting")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "setting_Name", length = 50, nullable = false)
    private String settingName;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
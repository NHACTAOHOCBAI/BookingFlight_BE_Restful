package com.bookingflight.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingflight.demo.entity.SeatClass;

@Repository
public interface SeatClassRepository extends JpaRepository<SeatClass, String> {
    boolean existsByClassName(String className);
}

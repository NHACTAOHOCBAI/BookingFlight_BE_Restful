package com.bookingflight.demo.repository;

import com.bookingflight.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>  {
    boolean existsByIdCard(String idCard);
}

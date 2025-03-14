package com.bookingflight.demo.repository;

import com.bookingflight.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByUserName(String userName);
//    boolean existsByAirportCode( String airportCode);
}

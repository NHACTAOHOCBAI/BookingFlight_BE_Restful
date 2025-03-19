package com.bookingflight.demo.mapper;

import com.bookingflight.demo.dto.request.CustomerRequest;
import com.bookingflight.demo.dto.response.CustomerResponse;
import com.bookingflight.demo.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest customerRequest);
    CustomerResponse toCustomerResponse(Customer customer);
    void updateCustomer(Customer customer, CustomerRequest customerRequest);
}

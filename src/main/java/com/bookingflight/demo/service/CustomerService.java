package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.CustomerRequest;
import com.bookingflight.demo.dto.response.CustomerResponse;
import com.bookingflight.demo.entity.Customer;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.CustomerMapper;
import com.bookingflight.demo.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest request) {
        if (customerRepository.existsByIdCard(request.getIdCard()))
            throw new AppException(ErrorCode.CUSTOMER_EXISTED);
        Customer customer = customerMapper.toCustomer(request);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toCustomerResponse).collect(Collectors.toList());
    }

    public CustomerResponse getCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponse updateCustomer(String id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        customerMapper.updateCustomer(customer, request);
        return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponse deleteCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        customerRepository.deleteById(id);
        return null;
    }
}

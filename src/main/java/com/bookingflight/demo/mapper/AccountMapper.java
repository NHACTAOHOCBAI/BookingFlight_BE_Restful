package com.bookingflight.demo.mapper;

import com.bookingflight.demo.dto.request.AccountRequest;
import com.bookingflight.demo.dto.response.AccountResponse;
import com.bookingflight.demo.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountRequest accountRequest);
    AccountResponse toAccountResponse(Account account);
    @Mapping(target = "userName", ignore = true)
    void updateAccount(@MappingTarget Account account, AccountRequest request);
}

package com.bookingflight.demo.service;

import com.bookingflight.demo.dto.request.AccountRequest;
import com.bookingflight.demo.dto.response.AccountResponse;
import com.bookingflight.demo.entity.Account;
import com.bookingflight.demo.exception.AppException;
import com.bookingflight.demo.exception.ErrorCode;
import com.bookingflight.demo.mapper.AccountMapper;
import com.bookingflight.demo.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    public AccountResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        // tao encoder bcrypt
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Account account = accountMapper.toAccount(request);
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::toAccountResponse).collect(Collectors.toList());
    }

    public AccountResponse getAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        return accountMapper.toAccountResponse(account);
    }

    public Void deleteAccount(String id) {
        accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        accountRepository.deleteById(id);
        return null;
    }

    public AccountResponse updateAccount(String id, AccountRequest request) {
        Account account= accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        accountMapper.updateAccount(account,request);
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }
}

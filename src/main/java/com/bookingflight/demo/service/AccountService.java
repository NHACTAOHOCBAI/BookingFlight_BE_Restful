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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public AccountResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        // tao encoder bcrypt
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Account account = accountMapper.toAccount(request);
        try {
            dateFormat.setLenient(false);// giup xet chat che dob
            Date dob = dateFormat.parse(request.getDateOfBirth());
            account.setDateOfBirth(dob);
        }
        catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_BIRTH);
        }
        // kiem tra dinh dang dob
        Account savedAccount = accountRepository.save(account);
        AccountResponse accountResponse= accountMapper.toAccountResponse(savedAccount);
        accountResponse.setDateOfBirth(dateFormat.format(savedAccount.getDateOfBirth()));
        return accountResponse;
    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> {
            AccountResponse accountResponse = accountMapper.toAccountResponse(account);
            accountResponse.setDateOfBirth(dateFormat.format(account.getDateOfBirth()));
            return accountResponse;
        }).collect(Collectors.toList());
    }

    public AccountResponse getAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        AccountResponse accountResponse = accountMapper.toAccountResponse(account);
        accountResponse.setDateOfBirth(dateFormat.format(account.getDateOfBirth()));
        return accountResponse;
    }

    public Void deleteAccount(String id) {
        accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        accountRepository.deleteById(id);
        return null;
    }

    public AccountResponse updateAccount(String id, AccountRequest request) {
        Account account= accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        accountMapper.updateAccount(account,request);
        try {
            dateFormat.setLenient(false);// giup xet chat che dob
            Date dob = dateFormat.parse(request.getDateOfBirth());
            account.setDateOfBirth(dob);
        }
        catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_BIRTH);
        }
        Account updatedAccount = accountRepository.save(account);
        AccountResponse accountResponse= accountMapper.toAccountResponse(updatedAccount);
        accountResponse.setDateOfBirth(dateFormat.format(updatedAccount.getDateOfBirth()));
        return accountResponse;
    }
}

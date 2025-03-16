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

    public AccountResponse createAccount( AccountRequest request) {
        if (accountRepository.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        // hash password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        // set Dob
        Account account = accountMapper.toAccount(request);
        account.setDateOfBirth(parseDate(request.getDateOfBirth()));
        // luu
        Account savedAccount = accountRepository.save(account);
        // mapper
        AccountResponse accountResponse = accountMapper.toAccountResponse(savedAccount);
        accountResponse.setDateOfBirth(formatDate(savedAccount.getDateOfBirth()));
        return accountResponse;
    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> {
            AccountResponse accountResponse = accountMapper.toAccountResponse(account);
            accountResponse.setDateOfBirth(formatDate(account.getDateOfBirth()));
            return accountResponse;
        }).collect(Collectors.toList());
    }

    public AccountResponse getAccount(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        AccountResponse accountResponse = accountMapper.toAccountResponse(account);
        accountResponse.setDateOfBirth(formatDate(account.getDateOfBirth()));
        return accountResponse;
    }

    public Void deleteAccount(String id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        accountRepository.deleteById(id);
        return null;
    }

    public AccountResponse updateAccount(String id, AccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXISTED));
        accountMapper.updateAccount(account, request);
        account.setDateOfBirth(parseDate(request.getDateOfBirth()));

        Account updatedAccount = accountRepository.save(account);
        AccountResponse accountResponse = accountMapper.toAccountResponse(updatedAccount);
        accountResponse.setDateOfBirth(formatDate(updatedAccount.getDateOfBirth()));
        return accountResponse;
    }

    // phuong thuc chuyen string sang date
    private Date parseDate(String dateStr) {
        try {
            dateFormat.setLenient(false);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_BIRTH);
        }
    }
    // phuong thuc chuyen date sang string
    private String formatDate(Date date) {
        return dateFormat.format(date);
    }
}

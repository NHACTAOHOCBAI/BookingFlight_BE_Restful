package com.bookingflight.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookingflight.demo.dto.request.APIResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    // xu ly exception khong xac dinh
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<APIResponse<String>> handleUnidentifiedException() {
        APIResponse<String> response = new APIResponse<>();
        response.setMessage(ErrorCode.UNIDENTIFIED_EXCEPTION.getMessage());
        response.setCode(ErrorCode.UNIDENTIFIED_EXCEPTION.getCode());
        return ResponseEntity.badRequest().body(response);
    }

    // xu ly exception thong thuong
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<APIResponse<String>> handleAppException(AppException exception) {
        APIResponse<String> response = new APIResponse<>();
        response.setMessage(exception.getErrorCode().getMessage());
        response.setCode(exception.getErrorCode().getCode());
        return ResponseEntity.badRequest().body(response);
    }

    // xu ly validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse<String>> handleValidException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        // lay ra duoc enumKey
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        // tao ra duoc errorCode nho vao mess truyen vao
        APIResponse<String> response = new APIResponse<>();
        response.setMessage(errorCode.getMessage());
        response.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(response);
    }
}

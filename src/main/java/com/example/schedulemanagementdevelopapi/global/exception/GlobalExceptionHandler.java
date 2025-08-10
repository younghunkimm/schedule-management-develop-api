package com.example.schedulemanagementdevelopapi.global.exception;

import com.example.schedulemanagementdevelopapi.global.dto.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusiness(
            BusinessException ex,
            HttpServletRequest request
    ) {

        ErrorType errorType = ex.getErrorType();

        return ResponseEntity
                .status(errorType.getHttpStatus())
                .body(
                        ErrorResponseDto.of(
                                errorType.getHttpStatus().value(),
                                errorType.getCode(),
                                errorType.getMessage(),
                                request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        Map<String, String> data = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (a,b) -> a,
                        LinkedHashMap::new
                ));

        return ResponseEntity
                .status(CommonErrorCode.VALIDATION_FAILED.getHttpStatus())
                .body(
                        ErrorResponseDto.of(
                                CommonErrorCode.VALIDATION_FAILED.getHttpStatus().value(),
                                CommonErrorCode.VALIDATION_FAILED.getCode(),
                                CommonErrorCode.VALIDATION_FAILED.getMessage(),
                                request.getRequestURI(),
                                data
                        )
                );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request
    ) {

        return ResponseEntity
                .status(CommonErrorCode.VALIDATION_FAILED.getHttpStatus())
                .body(
                        ErrorResponseDto.of(
                                CommonErrorCode.VALIDATION_FAILED.getHttpStatus().value(),
                                CommonErrorCode.VALIDATION_FAILED.getCode(),
                                CommonErrorCode.VALIDATION_FAILED.getMessage(),
                                request.getRequestURI(),
                                Map.of(
                                        "invalidValue", String.valueOf(ex.getValue())
                                )
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            Exception ex,
            HttpServletRequest request
    ) {

        return ResponseEntity
                .status(CommonErrorCode.INTERNAL_ERROR.getHttpStatus())
                .body(
                        ErrorResponseDto.of(
                                CommonErrorCode.INTERNAL_ERROR.getHttpStatus().value(),
                                CommonErrorCode.INTERNAL_ERROR.getCode(),
                                CommonErrorCode.INTERNAL_ERROR.getMessage(),
                                request.getRequestURI()
                        )
                );
    }
}

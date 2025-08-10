package com.example.schedulemanagementdevelopapi.global.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorType errorType) {
        super(errorType);
    }
}

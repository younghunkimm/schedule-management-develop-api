package com.example.schedulemanagementdevelopapi.global.exception;

public class UnAuthorizedException extends BusinessException {
    public UnAuthorizedException(ErrorType errorType) {
        super(errorType);
    }
}

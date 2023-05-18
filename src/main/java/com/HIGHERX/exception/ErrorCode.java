package com.HIGHERX.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER_ACCOUNT(HttpStatus.CONFLICT, "User account is duplicated"),
    DUPLICATED_USER_NICKNAME(HttpStatus.CONFLICT, "User nickname is duplicated"),
    DUPLICATED_USER_CRN(HttpStatus.CONFLICT, "User Crn is duplicated"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Password is invalid"),
    INVALID_CRN(HttpStatus.UNAUTHORIZED, "CRN is invalid"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is invalid"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "Permission is invalid"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),

    ALREADY_LIKED(HttpStatus.CONFLICT, "User already liked the post")
    ;

    private HttpStatus status;
    private String message;
}
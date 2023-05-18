package com.HIGHERX.controller.request.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthLoginRequest {
    private String account;
    private String password;
}

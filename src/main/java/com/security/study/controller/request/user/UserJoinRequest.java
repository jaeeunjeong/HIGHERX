package com.security.study.controller.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String account;
    private String password;
    private String nickname;
    private String phone;
    private String crn;
}

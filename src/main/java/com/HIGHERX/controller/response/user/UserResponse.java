package com.HIGHERX.controller.response.user;

import com.HIGHERX.dto.user.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {
    private String nickname;
    private String phone;
    private String crn;
    private Timestamp createdAt;

    public static UserResponse fromDto(UserDto user) {
        UserResponse res = new UserResponse();

        res.nickname = user.getNickname();
        res.phone = user.getPhone();
        res.crn = user.getCrn();
        res.createdAt= user.getCreatedAt();

        return res;
    }
}

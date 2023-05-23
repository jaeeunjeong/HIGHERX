package com.security.study.fixture;

import com.security.study.entity.task.TaskEntity;
import com.security.study.entity.user.UserEntity;

public class UserEntityFixture {

    public static UserEntity createUser() {
        return UserEntity.of("account", "password", "nickname", "phone", "crn");
    }

    public static UserEntity createUserAccount(String account) {
        return UserEntity.of(account, "password1", "nickname1", "phone1", "crn1");
    }

    public static UserEntity createUserNickname(String nickname) {
        return UserEntity.of("account1", "password1", nickname, "phone1", "crn1");
    }

    private static UserEntity createUserCrn(String crn) {
        return UserEntity.of("account1", "password1", "nickname1", "phone1", crn);
    }
}

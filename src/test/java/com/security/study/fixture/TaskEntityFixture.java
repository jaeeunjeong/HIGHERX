package com.security.study.fixture;

import com.security.study.entity.task.TaskEntity;
import com.security.study.entity.user.UserEntity;

public class TaskEntityFixture {

    public static TaskEntity get(String account, String password, String nickname, String phone, String title, String content) {
        UserEntity user = UserEntity.of(account, password, nickname, phone);
        TaskEntity task = TaskEntity.of(title, content, user);
        return task;
    }
}
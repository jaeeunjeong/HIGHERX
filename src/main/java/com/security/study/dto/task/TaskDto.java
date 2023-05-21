package com.security.study.dto.task;

import com.security.study.dto.user.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskDto {
    private Long id;
    private String title;
    private String content;
    private UserDto userDto;
}

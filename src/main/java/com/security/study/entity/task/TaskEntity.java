package com.security.study.entity.task;

import com.security.study.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public static TaskEntity of(String title, String content, UserEntity user) {
        TaskEntity entity = new TaskEntity();
        entity.title = title;
        entity.content = content;
        user.getTasks().add(entity);
        entity.user = user;
        return entity;
    }
}

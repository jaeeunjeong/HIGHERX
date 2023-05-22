package com.security.study.entity.task;

import com.security.study.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
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

    public TaskEntity(String title, String content, UserEntity user) {
        TaskEntity entity = new TaskEntity();
        this.title = title;
        this.content = content;
        user.getTasks().add(entity);
        this.user = user;
    }
}

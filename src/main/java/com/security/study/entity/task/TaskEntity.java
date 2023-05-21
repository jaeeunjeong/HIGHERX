package com.security.study.entity.task;

import com.security.study.entity.user.UserEntity;
import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;

@Entity
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

}

package com.security.study.entity.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String account;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 30, unique = true)
    private String crn;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    protected UserEntity() {
    }

    @PrePersist
    void createAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static UserEntity of(String account, String password, String nickname, String phone, String crn) {

        UserEntity entity = new UserEntity();

        entity.account = account;
        entity.password = password;
        entity.nickname = nickname;
        entity.phone = phone;
        entity.crn = crn;

        return entity;
    }

    public void deleteUser() {
        this.phone = "************";
        this.crn = "*************";
        this.password = "*********";
    }
}

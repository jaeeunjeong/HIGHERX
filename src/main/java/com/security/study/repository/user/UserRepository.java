package com.security.study.repository.user;

import com.security.study.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByAccount(String account);

    Optional<Object> findByNickname(String nickname);
}

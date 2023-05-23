package com.security.study.repository.user;

import com.security.study.entity.user.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static com.security.study.fixture.UserEntityFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager em;

    private void clear() {
        em.flush();
        em.clear();
    }

    @DisplayName("회원 가입 및 조회")
    @Test
    void test1() {
        // given
        UserEntity user = createUser();

        // when
        userRepository.save(user);
        clear();

        // then
        UserEntity findUser = userRepository.findById(user.getId()).orElseThrow(RuntimeException::new);
        assertThat(findUser.getId()).isEqualTo(user.getId());

    }

    @DisplayName("account가 중복인 경우 회원가입 x")
    @Test
    void test3() {
        // given
        UserEntity user = createUser();
        userRepository.save(user);
        clear();

        // when, then
        assertThatThrownBy(() -> userRepository.save(createUserAccount(user.getAccount())))
            .isInstanceOf(DataIntegrityViolationException.class);

    }

    @DisplayName("nickname이 중복인 경우 회원 가입 x")
    @Test
    void test4() {
        // given
        UserEntity user = createUser();
        userRepository.save(user);
        clear();

        // when, then
        assertThatThrownBy(() -> userRepository.save(createUserNickname(user.getNickname())))
            .isInstanceOf(DataIntegrityViolationException.class);
    }

    @DisplayName("user 삭제 확인")
    @Test
    void test5() {
        // given
        UserEntity user = userRepository.saveAndFlush(createUser());

        // when
        user.deleteUser();
        userRepository.save(user);
        userRepository.flush();

        // then
        UserEntity result = userRepository.findById(user.getId()).orElseThrow(RuntimeException::new);
        assertThat(result.getPhone()).startsWith("***");
        assertThat(result.getPassword()).startsWith("***");
        assertThat(result.getCrn()).startsWith("***");
    }

    @DisplayName("user 삭제와 Task 도 삭제 되는지 확인")
    @Test
    void test6() {
        // given


        // when


        // then
    }

}
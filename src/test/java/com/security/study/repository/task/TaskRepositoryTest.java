package com.security.study.repository.task;

import com.security.study.controller.response.task.TaskRepository;
import com.security.study.entity.task.TaskEntity;
import com.security.study.entity.user.UserEntity;
import com.security.study.fixture.UserEntityFixture;
import com.security.study.repository.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager em;


    @DisplayName("할 일 생성 후 할 일 id 기준 조회 확인")
    @Test
    void test1() {
        // given
        String titie = "title";
        String content = "content";

        // when
        TaskEntity task = TaskEntity.of(titie, content, mock(UserEntity.class));
        taskRepository.save(task);

        // then
        TaskEntity result = taskRepository.findById(task.getId()).orElseThrow(RuntimeException::new);
        assertThat(task.getId()).isEqualTo(result.getId());

    }

    @DisplayName("사용자와 제대로 연결되었는지 확인하고 사용자가 할 일을 갖고 있는지 확인")
    @Test
    void test2() {
        String titie = "title";
        String content = "content";
        UserEntity user = UserEntityFixture.createUser();
        userRepository.save(user);

        // when
        TaskEntity task = TaskEntity.of(titie, content, user);
        taskRepository.save(task);

        // then
        TaskEntity result = taskRepository.findById(task.getId()).orElseThrow(RuntimeException::new);
        assertThat(user.getId()).isEqualTo(result.getUser().getId());
        assertThat(user.getTasks().get(0).getId()).isEqualTo(result.getId());


    }
}
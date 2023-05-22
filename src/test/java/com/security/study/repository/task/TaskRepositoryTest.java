package com.security.study.repository.task;

import com.security.study.controller.response.task.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @PersistenceContext
    EntityManager em;


    @DisplayName("할 일 생성 후 할 일 id 기준 조회 확인")
    @Test
    void test1(){

    }

    @DisplayName("사용자와 제대로 연결되었는지 확인하고 사용자가 할 일을 갖고 있는지 확인")
    @Test
    void test2(){

    }
}
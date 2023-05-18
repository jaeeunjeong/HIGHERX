package com.HIGHERX.util;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    @DisplayName("비밀번호가 맞는지 확인")
    void test1(){
        String password = "password";
        String encodePassword = encoder.encode(password);

        boolean isMatch = encoder.matches(password, encodePassword);

        assertThat(isMatch).isTrue();
    }
}

package com.HIGHERX.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CrnVerifyUtilsTest {
    @Test
    @DisplayName("사업자 등록 번호를 확인한다.")
    void test1(){
        CrnVerifyUtils crnVerifyUtils = new CrnVerifyUtils();
        assertThat(crnVerifyUtils.crnVerify("123-45-67891")).isTrue();
    }

}
package com.polarbookshop.catalogservice;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//Book 객체의 유효성 검사 제약조건을 검증하기 위한 단위 테스트
public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        System.out.println("BeforeAll 실행됨!");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testDummy() {
        System.out.println("테스트 실행됨!");  // ✅ JUnit 5 실행 확인
    }
}

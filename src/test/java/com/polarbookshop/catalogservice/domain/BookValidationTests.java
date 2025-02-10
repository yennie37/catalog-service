package com.polarbookshop.catalogservice.domain;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

//Book 객체의 유효성 검사 제약조건을 검증하기 위한 단위 테스트
public class BookValidationTests {
    private static Validator validator;

    // @BeforeAll: 클래스 내의 테스트를 실행하기 전에 가장 먼저 실행할 코드 블록임을 나타냄.
    @BeforeAll
    static void setUp() {
        System.out.println("BeforeAll 실행됨!");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test // 테스트 케이스임을 나타냄
    void whenAllFieldsCorrectThenValidationSucceeds() {
        System.out.println("정상 데이터 테스트 케이스 실행");
        var book = new Book("0104567890", "제목쏼라쏼라", "김작가",9.80);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty(); // 유효성 검사에서 오류 없음 확인
    }

    @Test // 테스트 케이스임을 나타냄
    void whenIsbnDefinedIncorrectThenValidationFails() {
        System.out.println("비정상 데이터 테스트 케이스 실행");
        // 유효하지않은 ISBN 입력
        var book = new Book("ab45678d90", "제목쏼라", "정작가",8.70);

        // book 객체의 유효성을 검사하고, 제약 조건을 위반한 항목들을 Set<ConstraintViolation<Book>>에 저장.
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        System.out.println("[디버깅] violations: " + violations);

        // 오류가 1개인지 확인
        assertThat(violations).hasSize(1);
        // 만약 getMessage()의 실제 값이 "The book ISBN must be defined"이면, 테스트는 실패
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid");


    }
}

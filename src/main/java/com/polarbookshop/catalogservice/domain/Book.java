package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

// 도메인 객체(Entity) 정의
// 도메인 모델은 불가변 객체인 레코드로 구현
public record Book(
        @NotBlank(message = "The book ISBN must be defined")
        @Pattern(
                regexp = "^[0-9]{10}|[0-9]{13}$",
                message = "The ISBN format must be valid"
        )
        String isbn,    // 책 고유번호(식별)
        @NotBlank(message = "The book title must be defined")
        String title,
        @NotBlank(message = "The book author must be defined")
        String author,
        @NotNull(message = "The book price must be defined")
        // @Positive: 널 값이어서는 안되고 0보다 큰 값이어야함.
        @Positive(message = "The book price must be greater than zero")
        Double price
) { }

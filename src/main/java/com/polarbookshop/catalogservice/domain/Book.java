package com.polarbookshop.catalogservice.domain;

// 도메인 객체(Entity) 정의
// 도메인 모델은 불가변 객체인 레코드로 구현
public record Book(
        String isbn,    // 책 고유번호(식별)
        String title,
        String author,
        Double price
) { }

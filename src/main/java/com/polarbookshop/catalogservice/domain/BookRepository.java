package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

// 데이터 액세스를 위해 리포지터리 추상화
public interface BookRepository {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}

package com.polarbookshop.catalogservice.persistence;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// BookRepository 인터페이스의 인메모리 구현
// @Repository: 클래스가 스프링에 의해 관리되는 리포지터리임을 표시
@Repository
public class InMemoryBookRepository implements BookRepository {
    // 테스트 목적으로 책을 저장하기 위한 인메모리 앱
    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        // Optional.of() : 값이 존재할 때 Optional<T> 객체를 생성
        // Optional.empty() : 값이 없을 때 Optional<T> 객체를 생성
        return existsByIsbn(isbn) ?  Optional.of(books.get(isbn)) : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return books.get(isbn) != null;
    }

    @Override
    public Book save(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }

}

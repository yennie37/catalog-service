package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

// 애플리케이션의 사용사례 구현
// @Service : 해당 클래스가 스프링이 관리하는 서비스임을 표시하는 스트레오타입 애너테이션
@Service
public class BookService {
    // 스프링 4.3부터 생성자가 하나일 경우 @Autowired 애너테이션 생략 가능
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository; // BookRepository를 생성자 오토와이어링을 통해 제공
    }
    // Iterable<T>는 자바의 최상위 반복(iterable) 인터페이스. for-each 루프에서 사용할 수 있는 객체
    public Iterable<Book> viewBookList() {
        // findAll(): Spring Boot + JPA 환경에서 많이 사용되는 메서드로, 데이터베이스에 저장된 모든 Book 객체를 조회
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        // 존재하지 않는 책을 보려할때 예외처리
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        // 동일한 책을 여러 번 추가하려하면 예외발생
        if(bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }
    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn).map(existingBook -> {
            // 책을 수정할 때 개체 식별자인 ISBN 코드를 제외한 모든 필드 수정가능.
            var bookToUpdate = new Book(
                    existingBook.isbn(),
                    book.title(),
                    book.author(),
                    book.price());
            return bookRepository.save(bookToUpdate);
        }).orElseGet(() -> addBookToCatalog(book));
    }
}

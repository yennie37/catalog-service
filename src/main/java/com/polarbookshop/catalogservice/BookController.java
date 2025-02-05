package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import com.polarbookshop.catalogservice.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    // 루트 패스 URI에 추가되는 URI 템플릿 변수("/books/{isbn}")
    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    // 책이 성공적으로 생성되면 201 상태코드를 반환
    @ResponseStatus(HttpStatus.CREATED) // 성공시 201 상태코드 반환
    //@RequestBody는 웹 요청의 본문을 메서드 변수로 바인드
    public Book post(@RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 성공시 204 상태코드 반환
    public void delete(@PathVariable String isbn, @RequestBody Book book) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    //@RequestBody : 요청 본문(JSON 형식의 데이터를 Book 객체로 변환)하여 컨트롤러 메서드에 전달
    public Book put(@PathVariable String isbn, @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }

}

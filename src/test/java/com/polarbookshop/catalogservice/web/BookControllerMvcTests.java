package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.BookController;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

// 수동 임포트
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 스프링 MVC 컴포넌트에 중점을 두고, 명시적으로는 BookController 클래스를 타깃으로하는 테스트 클래스임을 나타냄.
@WebMvcTest(BookController.class)
public class BookControllerMvcTests {
    // 모의 환경에서 웹 계층을 테스트하기 위한 유틸리티 클래스
    @Autowired
    private MockMvc mockMvc;

    // 스프링 3.4.0 버전부터는 @MockBean이 @MockitoBean으로 변경되었음
    // 스프링 애플리케이션 콘텍스트에 BookService의 모의 객체를 추가.
    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "73737319951";

        given(bookService.viewBookDetails(isbn))
                // 모의 빈이 어떻게 작동할지 규정
                .willThrow(BookNotFoundException.class);
        mockMvc
                // MockMVC는 HTTP GET 요청을 수행하고 결과를 확인하기 위해 사용
                .perform(get("/books/" + isbn))
                // 응답이 "404 발견되지 않음" 상태를 가질 것으로 예상
                .andExpect(status().isNotFound());
    }
}

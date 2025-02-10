package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.assertThat;

// JSON 직렬화에 중점을 둔 테스트 클래스 표시
@JsonTest
public class BookJsonTests {
    @Autowired
    private JacksonTester<Book> json;

    private static final Logger log = LoggerFactory.getLogger(BookJsonTests.class);

    @Test
    void testSerialize() throws Exception {
        var book = new Book("0011154321", "신세계요" ,"이작가", 8.90);
        var jsonContent = json.write(book);

        // JsonPath 형식을 사용해 JSON 객체를 탐색하고 자바의 JSON 변환 확인
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());

        log.info("testSerialize 이 실행됨");
    }

    @Test
    void testDeserialize() throws Exception {
        // 자바 텍스트 블록 기능을 사용해 JSON 객체 정의
        var content = """
            {
                "isbn": "0011154321",
                "title": "신세계요",
                "author": "이작가",
                "price": 8.90
            }
            """;
        // JSON에서 자바 객체로의 변환 확인
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book("0011154321", "신세계요", "이작가", 8.90));

        log.info("testDeserialize 이 실행됨");
    }
}

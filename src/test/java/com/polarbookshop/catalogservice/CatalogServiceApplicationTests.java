package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

// 스프링 부트 애플리케이션을 테스트하기 위한 셋업 제공
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {
	@Autowired
	// 테스트를 위해 REST엔드포인트를 호출할 유틸리티
	private WebTestClient webTestClient;

	@Test // 테스트케이스 식별
	void whenPostRequestThenBookCreated() {
		var expectedBook = new Book("1231231231", "제모기다", "박작가", 8.50);

		/*
			/books로 POST 요청을 보냄.
			expectedBook 객체를 요청 본문으로 보냄.
			응답 상태 코드가 201 Created인지 확인.
			응답 본문이 Book 객체인지 확인.
			응답으로 받은 Book 객체가 null이 아닌지 검증.
			생성된 Book 객체의 isbn이 예상한 값과 같은지 확인.
		* */
		webTestClient
				.post()	// HTTP POST 요청
				.uri("/books") // 해당 엔드포인트로 요청
				// HTTP 요청 본문에 expectedBook 객체를 JSON으로 변환하여 추가
				.bodyValue(expectedBook)
				// 요청 전송 실행(비동기 요청이므로 응답을 기다리지 않고 즉시 반환)
				.exchange()
				// HTTP 응답이 "201 Created" 상태인지 확인
				.expectStatus().isCreated()
				// expectBody(Book.class): HTTP 응답 본문을 Book 객체로 변환하여 가져옴
				// value(actualBook -> {}): 응답 본문을 actualBook으로 받아서 검증하는 람다 함수 실행.
				.expectBody(Book.class).value(actualBook -> {
					// HTTP 응답 본문이 널 값인지 아닌지 확인(null이면 테스트 실패)
					assertThat(actualBook).isNotNull();
					// 생성된 객체가 예상과 같은지 확인
					assertThat(actualBook.isbn()).isEqualTo(actualBook.isbn());
				});
	}

}

package com.polarbookshop.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication
 스프링 설정 클래스를 정의하고 컴포넌트 스캔과 스프링부트 자동설정을 실행. 아래 세 애너테이션 포함
 @Configuration: 해당 클래스가 빈을 정의하는 클래스임을 명시
 @ComponentScan: 컴포넌트 검색을 통해 비능ㄹ 찾아 스프링 콘텍스트에 자동으로 등록
 @EnableAutoConfiguration: 스프링 부트에서 제공하는 자동 설정 기능 활성화
*/
@SpringBootApplication
public class CatalogServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
		System.out.println("! Main is Running !");
	}
}

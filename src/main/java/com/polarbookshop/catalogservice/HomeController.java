package com.polarbookshop.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/") // 루트 엔드포인트로 GET요청 처리
    public String getGreeting() {
        return "Welcome to polarbookshop's catalog service!";
    }
}

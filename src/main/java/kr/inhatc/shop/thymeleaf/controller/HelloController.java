package kr.inhatc.shop.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "thymeleaf/index";
    }

}

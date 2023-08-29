package kr.inhatc.shop.thymeleaf.controller;

import kr.inhatc.shop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/ex1")
    public String ex1(Model model){
        // 문자열 전달
        model.addAttribute("data", "thymeleaf");

        // 객체 전달
        Point point = new Point(10, 20);
        model.addAttribute("point", point);
        return "thymeleaf/ex1";
    }

    @GetMapping("/ex2")
    public String ex2(Model model){
        ItemDto itemDto = ItemDto.builder()
                .itemNm("테스트 상품1")
                .price(10000)
                .itemDetail("테스트 상품 상세 설명")
                .regTime(LocalDateTime.now())
                .build();

        model.addAttribute("itemDto", itemDto);

        return "thymeleaf/ex2";
    }
}

package kr.inhatc.shop.test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestDto {
    //깃허브 커밋 테스트
    private String name;
    private int age;
}

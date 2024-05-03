package com.study.kioskback.controller;

import com.study.kioskback.dto.UserSaveDto;
import com.study.kioskback.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/point")
    public void checkUserPoint(@RequestBody UserSaveDto dto,
                               @RequestParam("userPhoneNumber") String userPhoneNumber,
                               @RequestParam("productPrice") int productPriceSum){
        // productPrice를 총 주문 금액으로 변경 필요.
        int point = productPriceSum / 100;
        userService.updatePoint(dto, point);        // 적립
    }
}

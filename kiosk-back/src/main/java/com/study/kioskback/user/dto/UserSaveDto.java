package com.study.kioskback.user.dto;

import com.study.kioskback.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.study.kioskback.user.domain.UserPointConst.DEFAULT_POINT;

@Data
@NoArgsConstructor
public class UserSaveDto {
    private String userPhoneNumber;

    public User toEntity(){
        return User.builder()
                .userPhoneNumber(this.userPhoneNumber)
                .userPoint(DEFAULT_POINT)
                .userJoinDate(LocalDate.now())
                .build();
    }

}
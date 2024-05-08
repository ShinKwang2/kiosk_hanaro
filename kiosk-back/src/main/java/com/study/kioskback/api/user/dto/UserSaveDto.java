package com.study.kioskback.api.user.dto;

import com.study.kioskback.api.user.domain.User;
import com.study.kioskback.api.user.domain.UserConst;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserSaveDto {
    private String userPhoneNumber;

    public User toEntity(){
        return User.builder()
                .userPhoneNumber(this.userPhoneNumber)
                .userPoint(UserConst.DEFAULT_POINT)
                .userJoinDate(LocalDate.now())
                .build();
    }

}
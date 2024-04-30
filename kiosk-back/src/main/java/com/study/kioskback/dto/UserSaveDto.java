package com.study.kioskback.dto;

import com.study.kioskback.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.study.kioskback.entity.user.UserPointConst.*;

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

package com.study.kioskback.api.admin.service.response;

import com.study.kioskback.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseForAdmin {

    private Integer id;
    private String phoneNumber;
    private Integer point;
    private LocalDateTime joinDate;

    @Builder
    private UserResponseForAdmin(Integer id, String phoneNumber, Integer point, LocalDateTime joinDate) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.joinDate = joinDate;
    }

    public static UserResponseForAdmin of(User user) {
        return UserResponseForAdmin.builder()
                .id(user.getUserId())
                .phoneNumber(user.getUserPhoneNumber())
                .point(user.getUserPoint())
                .joinDate(user.getUserJoinDate())
                .build();
    }
}

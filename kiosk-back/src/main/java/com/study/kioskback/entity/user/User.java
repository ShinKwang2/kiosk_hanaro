package com.study.kioskback.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;                                 // 회원 인덱스
    @Column(name = "user_phone_number", nullable = false)
    private String userPhoneNumber;                         // 회원 휴대폰 번호
    @Column(name = "user_point")
    private Integer userPoint;                              // 회원 포인트
    @Column(name = "user_join_date")
    private LocalDate userJoinDate;                         // 회원가입 일자

    @Builder
    public User(String userPhoneNumber, Integer userPoint, LocalDate userJoinDate) {
        this.userPhoneNumber = userPhoneNumber;
        this.userPoint = userPoint;
        this.userJoinDate = userJoinDate;
    }

    // user point 적립
    public Integer addPoint(Integer plusPoint) {
        userPoint += plusPoint;
        return userPoint;
    }
}


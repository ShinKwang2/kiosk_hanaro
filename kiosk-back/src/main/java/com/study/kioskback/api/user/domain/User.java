package com.study.kioskback.api.user.domain;

import com.study.kioskback.api.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "user_id", nullable = false)
    private Integer userId;                                 // 회원 인덱스
    @Column(name = "phone_number", nullable = false)
    private String userPhoneNumber;                         // 회원 휴대폰 번호
    @Column(name = "user_point")
    private Integer userPoint;                              // 회원 포인트

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Order> orders = new ArrayList<>();

    @Column(name = "user_created_date")
    private LocalDateTime userJoinDate;                         // 회원가입 일자

    @Builder
    public User(String userPhoneNumber, Integer userPoint, LocalDateTime userJoinDate) {
        this.userPhoneNumber = userPhoneNumber;
        this.userPoint = userPoint;
        this.userJoinDate = userJoinDate;
    }

    // user point 적립
    public Integer addPoint(Integer plusPoint) {
        userPoint += plusPoint;
        return userPoint;
    }

    //==연관관계 편의 메소드==//
    public void addOrder(Order order) {
        orders.add(order);
    }
}


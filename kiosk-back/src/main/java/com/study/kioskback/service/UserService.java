package com.study.kioskback.service;

import com.study.kioskback.dto.UserSaveDto;
import com.study.kioskback.entity.user.User;
import com.study.kioskback.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    // save
    @Transactional
    public void save(final UserSaveDto dto){
        userRepository.save(dto.toEntity());
    }

    // update point
    // 휴대폰 번호가 없으면 등록 후 적립, 있으면 적립
    @Transactional
    public void updatePoint(UserSaveDto dto, Integer point){
        User foundUser = userRepository.findByUserPhoneNumber(dto.getUserPhoneNumber())
                .orElseGet(() -> {
                    User newUser = dto.toEntity();
                    return userRepository.save(newUser);
                });
        foundUser.addPoint(point);
    }

    // userCheck
    @Transactional
    public void userCheck(String userPhoneNumber){
        User foundUser = userRepository.findByUserPhoneNumber(userPhoneNumber)
                .orElseThrow();
        // 본인인증 로직 추가
    }
}

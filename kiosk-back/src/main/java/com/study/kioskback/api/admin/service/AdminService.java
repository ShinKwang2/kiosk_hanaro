package com.study.kioskback.api.admin.service;

import com.study.kioskback.api.admin.service.response.UserResponseForAdmin;
import com.study.kioskback.api.user.domain.User;
import com.study.kioskback.api.user.domain.UserRepository;
import com.study.kioskback.error.exception.user.NotFoundUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {

    private final UserRepository userRepository;

    public List<UserResponseForAdmin> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseForAdmin::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer deleteUser(Integer id, LocalDateTime deletedDateTime) {
        User foundUser = userRepository.findById(id).orElseThrow(NotFoundUser::new);
        foundUser.delete(deletedDateTime);
        return foundUser.getUserId();
    }
}

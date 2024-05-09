package com.study.kioskback.api.admin.controller;

import com.study.kioskback.api.ApiResponse;
import com.study.kioskback.api.admin.service.AdminService;
import com.study.kioskback.api.admin.service.response.UserResponseForAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    public ApiResponse<List<UserResponseForAdmin>> getMembers() {
        return ApiResponse.ok(adminService.findAllUsers());
    }

    @DeleteMapping("/members/{id}")
    public ApiResponse<Integer> deleteMember(@PathVariable Integer id) {
        LocalDateTime deletedDateTime = LocalDateTime.now();
        return ApiResponse.ok(adminService.deleteUser(id, deletedDateTime));
    }

}

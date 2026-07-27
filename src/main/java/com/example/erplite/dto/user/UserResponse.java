package com.example.erplite.dto.user;

import com.example.erplite.enums.UserRole;
import com.example.erplite.enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;
    private String fullName;
    private String username;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime createdAt;

}
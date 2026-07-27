package com.example.erplite.service;

import com.example.erplite.dto.user.UserCreateRequest;
import com.example.erplite.dto.user.UserResponse;
import com.example.erplite.dto.user.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserCreateRequest request);

    List<UserResponse> getAll();

    UserResponse getById(UUID id);

    UserResponse update(UUID id, UserUpdateRequest request);

    void delete(UUID id);

}
package com.example.erplite.service.impl;

import com.example.erplite.dto.user.UserCreateRequest;
import com.example.erplite.dto.user.UserResponse;
import com.example.erplite.dto.user.UserUpdateRequest;
import com.example.erplite.entity.User;
import com.example.erplite.enums.UserStatus;
import com.example.erplite.exp.UserNotFoundException;
import com.example.erplite.exp.UsernameAlreadyExistsException;
import com.example.erplite.repository.UserRepository;
import com.example.erplite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(UserCreateRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setStatus(UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setFullName(savedUser.getFullName());
        response.setUsername(savedUser.getUsername());
        response.setRole(savedUser.getRole());
        response.setStatus(savedUser.getStatus());
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;

    }

    @Override
    public List<UserResponse> getAll() {

        List<User> users = userRepository.findAll();

        List<UserResponse> responseList = new ArrayList<>();

        for (User user : users) {

            UserResponse response = new UserResponse();

            response.setId(user.getId());
            response.setFullName(user.getFullName());
            response.setUsername(user.getUsername());
            response.setRole(user.getRole());
            response.setStatus(user.getStatus());
            response.setCreatedAt(user.getCreatedAt());

            responseList.add(response);
        }

        return responseList;

    }

    @Override
    public UserResponse getById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

    @Override
    public UserResponse update(UUID id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());

        User updatedUser = userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(updatedUser.getId());
        response.setFullName(updatedUser.getFullName());
        response.setUsername(updatedUser.getUsername());
        response.setRole(updatedUser.getRole());
        response.setStatus(updatedUser.getStatus());
        response.setCreatedAt(updatedUser.getCreatedAt());

        return response;

    }
    @Override
    public void delete(UUID id) {
        userRepository.delete(
                userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
    }


}
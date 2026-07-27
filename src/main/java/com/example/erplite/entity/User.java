package com.example.erplite.entity;


import com.example.erplite.enums.UserRole;
import com.example.erplite.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User extends Base{

    @Column(nullable = false,name = "fullName")
    private String fullName;
    @Column(name = "username",unique = true,nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;


}

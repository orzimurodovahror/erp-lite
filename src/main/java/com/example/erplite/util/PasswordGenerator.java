package com.example.erplite.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = "$2a$10$/1/MiSD42G65uVupSoMfeuGsjcuNnYHV8yzo4XoMa5DySfhyNCUj6";

        System.out.println(encoder.matches("042014", hash));
    }
}
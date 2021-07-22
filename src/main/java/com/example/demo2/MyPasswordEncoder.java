package com.example.demo2;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.printf("bar : %s\n", passwordEncoder.encode("bar"));
        System.out.printf("pass : %s\n", passwordEncoder.encode("pass"));
    }
}

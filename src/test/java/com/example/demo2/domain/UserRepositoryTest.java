package com.example.demo2.domain;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void createUser() {
        userRepository.save(User.builder()
                .email("skyer9@gmail.com")
                .password(passwordEncoder.encode("pass"))
                .firstName("San")
                .lastName("Lee")
                .roles("USER")
                .build());
    }
}

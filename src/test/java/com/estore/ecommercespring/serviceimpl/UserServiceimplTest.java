package com.estore.ecommercespring.serviceimpl;

import com.estore.ecommercespring.model.User;
import com.estore.ecommercespring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceimplTest {
    @Autowired
    private UserRepository underTest;
    private User user;
    private String email;

    @BeforeEach
    void setUp() {
        email = "JohnDoe@gmail.com";

        user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("JohnDoe@gmail.com")
                .password("1234")
                .build();
    }

    @Test
    void checkIfUserIsSaved() {

    }
    @Test
    void saveUser() {
    }

    @Test
    void authenticate() {
    }
}
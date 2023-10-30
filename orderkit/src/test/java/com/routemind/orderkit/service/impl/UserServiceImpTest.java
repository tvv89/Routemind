package com.routemind.orderkit.service.impl;

import com.routemind.orderkit.exception.UserNotFoundException;
import com.routemind.orderkit.model.entity.User;
import com.routemind.orderkit.repository.UserRepository;
import com.routemind.orderkit.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImpTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals(user, createdUser);
    }

    @Test
    void testFindUserByEmail() {
        String email = "test@example.com";
        User user = new User(); // Create a user object
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        User foundUser = userService.findUserByEmail(email);
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    void testUpdateUser() {
        Long id = 1L;
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(id, user);
        assertEquals(user, updatedUser);
    }

    @Test
    void testFindUserById() {
        Long id = 1L;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        User foundUser = userService.findUserById(id);
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    void testDeleteUserById() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(new User()));
        assertDoesNotThrow(() -> userService.deleteUserById(id));
        verify(userRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void testDeleteUserById_UserNotFoundException() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUserById(id));
    }
}
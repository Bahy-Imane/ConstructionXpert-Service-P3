package com.user;

import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserId(1L);
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user.getUsername(), users.get(0).getUsername());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(user.getUserId(), foundUser.getUserId());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_ShouldReturnNull_WhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User foundUser = userService.getUserById(1L);

        assertNull(foundUser);

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void createUser_ShouldReturnSavedUser() {
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_ShouldCallDeleteById() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals(user.getUserId(), updatedUser.getUserId());
        assertEquals(user.getUsername(), updatedUser.getUsername());

        verify(userRepository, times(1)).save(user);
    }
}

//package com.user;
//
//import com.user.controller.UserController;
//import com.user.model.User;
//import com.user.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UserControllerTest {
//
//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserService userService;
//
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        user = new User();
//        user.setUserId(1L);
//        user.setUsername("Imane Bahy");
//        user.setEmail("imanebahy@gmail.com");
//    }
//
//    @Test
//    void getAllUsers_ShouldReturnListOfUsers() {
//        List<User> users = Arrays.asList(user);
//        when(userService.getAllUsers()).thenReturn(users);
//
//        ResponseEntity<List<User>> response = userController.getAllUsers();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(users, response.getBody());
//        verify(userService, times(1)).getAllUsers();
//    }
//
//    @Test
//    void getUserById_UserExists_ShouldReturnUser() {
//        when(userService.getUserById(1L)).thenReturn(user);
//
//        ResponseEntity<User> response = userController.getUserById(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user, response.getBody());
//        verify(userService, times(1)).getUserById(1L);
//    }
//
//    @Test
//    void getUserById_UserNotFound_ShouldReturnNotFound() {
//        when(userService.getUserById(1L)).thenReturn(null);
//
//        ResponseEntity<User> response = userController.getUserById(1L);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//        verify(userService, times(1)).getUserById(1L);
//    }
//
//    @Test
//    void updateUser_ShouldReturnUpdatedUser() {
//        when(userService.updateUser(any(User.class))).thenReturn(user);
//
//        ResponseEntity<User> response = userController.updateUser(1L, user);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user, response.getBody());
//        verify(userService, times(1)).updateUser(user);
//    }
//
//    @Test
//    void deleteUser_ShouldReturnNoContent() {
//        doNothing().when(userService).deleteUser(1L);
//
//        ResponseEntity<Void> response = userController.deleteUser(1L);
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        verify(userService, times(1)).deleteUser(1L);
//    }
//}

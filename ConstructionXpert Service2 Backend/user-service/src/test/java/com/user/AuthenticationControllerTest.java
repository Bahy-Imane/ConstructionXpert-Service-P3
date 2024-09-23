//package com.user;
//
//import com.user.controller.AuthenticationController;
//import com.user.dto.AdminDTO;
//import com.user.dto.CustomerDTO;
//import com.user.dto.LoginUserDto;
//import com.user.exception.UserNotFoundException;
//import com.user.model.User;
//import com.user.service.AuthenticationService;
//import com.user.service.JwtService;
//import com.user.dto.LoginResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class AuthenticationControllerTest {
//
//    @InjectMocks
//    private AuthenticationController authenticationController;
//
//    @Mock
//    private JwtService jwtService;
//
//    @Mock
//    private AuthenticationService authenticationService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegister() {
//        CustomerDTO customerDTO = new CustomerDTO();
//        User user = new User();
//        when(authenticationService.signup(any(CustomerDTO.class))).thenReturn(user);
//
//        ResponseEntity<User> response = authenticationController.register(customerDTO);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user, response.getBody());
//        verify(authenticationService, times(1)).signup(customerDTO);
//    }
//
//    @Test
//    public void testAddAdmin() {
//        AdminDTO adminDTO = new AdminDTO();
//        User adminUser = new User();
//        when(authenticationService.addAdmin(any(AdminDTO.class))).thenReturn(adminUser);
//
//        ResponseEntity<User> response = authenticationController.addAdmin(adminDTO);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(adminUser, response.getBody());
//        verify(authenticationService, times(1)).addAdmin(adminDTO);
//    }
//
//    @Test
//    public void testAuthenticate_Success() {
//        LoginUserDto loginUserDto = new LoginUserDto();
//        LoginResponse loginResponse = new LoginResponse();
//        when(authenticationService.authenticate(any(LoginUserDto.class))).thenReturn(loginResponse);
//
//        ResponseEntity<?> response = authenticationController.authenticate(loginUserDto);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(loginResponse, response.getBody());
//        verify(authenticationService, times(1)).authenticate(loginUserDto);
//    }
//
//    @Test
//    public void testAuthenticate_UserNotFound() {
//        LoginUserDto loginUserDto = new LoginUserDto();
//        when(authenticationService.authenticate(any(LoginUserDto.class))).thenThrow(new UserNotFoundException("User not found"));
//
//        ResponseEntity<?> response = authenticationController.authenticate(loginUserDto);
//
//        // Assert
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//        assertEquals("User not found", response.getBody());
//        verify(authenticationService, times(1)).authenticate(loginUserDto);
//    }
//
//
//    @Test
//    public void testAuthenticate_InternalServerError() {
//        LoginUserDto loginUserDto = new LoginUserDto();
//        when(authenticationService.authenticate(any(LoginUserDto.class))).thenThrow(new RuntimeException("Some error"));
//
//        ResponseEntity<?> response = authenticationController.authenticate(loginUserDto);
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals("An error occurred during login.", response.getBody());
//        verify(authenticationService, times(1)).authenticate(loginUserDto);
//    }
//}

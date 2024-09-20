package com.user;

import com.user.dto.AdminDTO;
import com.user.dto.CustomerDTO;
import com.user.dto.LoginResponse;
import com.user.dto.LoginUserDto;
import com.user.enums.Role;
import com.user.mapper.UserMapper;
import com.user.model.Admin;
import com.user.model.Customer;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.AuthenticationService;
import com.user.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup_Success() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUsername("testUser");
        customerDTO.setEmail("test@example.com");
        customerDTO.setPassword("password");

        // Suppose Customer extends User
        Customer customer = new Customer();
        customer.setPassword("encodedPassword");

        when(userRepository.findByUsername(customerDTO.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toCustomerEntity(any(CustomerDTO.class))).thenReturn(customer);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(customer);

        // Act
        User savedUser = authenticationService.signup(customerDTO);

        // Assert
        assertNotNull(savedUser);
        verify(userRepository, times(1)).save(customer);
        assertEquals("encodedPassword", savedUser.getPassword());
    }

    @Test
    public void testSignup_UsernameExists() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUsername("existingUser");

        // Simuler le cas où le nom d'utilisateur existe déjà
        User existingUser = new Customer(); // Remplacer par Customer ou toute autre sous-classe de User si applicable
        when(userRepository.findByUsername(customerDTO.getUsername())).thenReturn(Optional.of(existingUser));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authenticationService.signup(customerDTO);
        });

        // Vérifiez que l'exception a bien été lancée avec le message attendu
        assertEquals("Username already exists", exception.getMessage());

        // Vérifiez que la méthode `save` du `userRepository` n'est jamais appelée
        verify(userRepository, times(0)).save(any(User.class));
    }


    @Test
    public void testSignup_EmailExists() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUsername("newUser");
        customerDTO.setEmail("existingEmail@example.com");

        when(userRepository.findByUsername(customerDTO.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.of(new User()));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticationService.signup(customerDTO);
        });

        assertEquals("Email already exists", exception.getMessage());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testAddAdmin_Success() {
        // Arrange
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setUsername("adminUser");
        adminDTO.setEmail("admin@example.com");
        adminDTO.setPassword("password");

        // Utilisez un objet Admin ici, puisque l'adminUser devrait être de type Admin
        Admin adminUser = new Admin();
        adminUser.setPassword("encodedPassword");

        when(userRepository.findByUsername(adminDTO.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(adminDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toAdminEntity(any(AdminDTO.class))).thenReturn(adminUser);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(adminUser);

        // Act
        User savedAdmin = authenticationService.addAdmin(adminDTO);

        // Assert
        assertNotNull(savedAdmin);
        verify(userRepository, times(1)).save(adminUser);
        assertEquals("encodedPassword", savedAdmin.getPassword());
    }

    @Test
    public void testAuthenticate_Success() {
        // Arrange
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUserNameOrEmail("testUser");
        loginUserDto.setPassword("password");

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(userRepository.findByUsernameOrEmail(anyString(), anyString()))
                .thenReturn(user);
        when(jwtService.generateToken(any(User.class), any()))
                .thenReturn("mockJwtToken");

        // Act
        LoginResponse response = authenticationService.authenticate(loginUserDto);

        // Assert
        assertNotNull(response);
        assertEquals("mockJwtToken", response.getToken());
        assertEquals(Role.USER, response.getRole());
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        // Arrange
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUserNameOrEmail("testUser");
        loginUserDto.setPassword("wrongPassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new UsernameNotFoundException("Invalid user credentials."));

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            authenticationService.authenticate(loginUserDto);
        });

        assertEquals("Invalid user credentials.", exception.getMessage());
    }

}

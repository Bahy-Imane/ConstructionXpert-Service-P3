package com.user.controller;

import com.user.dto.AdminDTO;
import com.user.dto.CustomerDTO;
import com.user.dto.LoginUserDto;
import com.user.exception.UserNotFoundException;
import com.user.dto.LoginResponse;
import com.user.model.User;
import com.user.service.AuthenticationService;
import com.user.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody CustomerDTO customerDTO) {
        User registeredUser = authenticationService.signup(customerDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-admin")
    public ResponseEntity<User> addAdmin(@RequestBody AdminDTO adminDTO) {
        User newAdmin = authenticationService.addAdmin(adminDTO);
        return ResponseEntity.ok(newAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            LoginResponse authenticatedUser = authenticationService.authenticate(loginUserDto);
            return ResponseEntity.ok(authenticatedUser);  // Return the full login response
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
        }
    }
//    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        try {
//            LoginResponse authenticatedUser = authenticationService.authenticate(loginUserDto);
//            Role role = authenticatedUser.getRole();
//
//            String jwtToken = jwtService.generateToken((UserDetails) authenticatedUser, role);
//
//            LoginResponse loginResponse = new LoginResponse();
//            loginResponse.setToken(jwtToken);
//            loginResponse.setRole(role);
//            //loginResponse.setExpiresIn(jwtService.getExpirationTime());
//
//            return ResponseEntity.ok(jwtToken);
//        } catch (UserNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
//        }
//    }
}

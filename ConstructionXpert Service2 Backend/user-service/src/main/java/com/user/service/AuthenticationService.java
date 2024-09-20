package com.user.service;

import com.user.dto.AdminDTO;
import com.user.dto.CustomerDTO;
import com.user.dto.LoginResponse;
import com.user.dto.LoginUserDto;
import com.user.mapper.UserMapper;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User signup(CustomerDTO input) {
        if (userRepository.findByUsername(input.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toCustomerEntity(input);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public User addAdmin(AdminDTO input) {
        if (userRepository.findByUsername(input.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toAdminEntity(input);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse authenticate(LoginUserDto input) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserNameOrEmail(),
                        input.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsernameOrEmail(input.getUserNameOrEmail(), input.getUserNameOrEmail());

            String token = jwtService.generateToken(user, user.getRole());
            return LoginResponse.builder()
                    .token(token)
                    .role(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user credentials.");
        }
    }



//    public LoginResponse authenticate(LoginUserDto input) {
//        Authentication authentication =  authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getUserNameOrEmail(),
//                        input.getPassword()
//                )
//        );
//        if (authentication.isAuthenticated()) {
//            User user = userRepository.findByUsernameOrEmail(input.getUserNameOrEmail(), input.getUserNameOrEmail());
//            String token = jwtService.generateToken(user,user.getRole());
//            return LoginResponse.builder()
//                    .token(token)
//                    .role(user.getRole())
//                    .build();
//        }
//        else {
//            throw new UsernameNotFoundException("Invalid user request.");
//        }
//
//    }
}

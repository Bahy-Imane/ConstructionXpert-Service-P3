package com.user.dto;

import com.user.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    //private long expiresIn;
    private Role role;
}

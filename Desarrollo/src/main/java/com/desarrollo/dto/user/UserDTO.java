package com.desarrollo.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDTO {
    private String username;
    private String password;
    private boolean isEnabled;
    private Set<String> roles;
}

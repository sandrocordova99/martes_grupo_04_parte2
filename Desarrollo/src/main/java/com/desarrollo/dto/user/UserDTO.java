package com.desarrollo.dto.user;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDTO {

    private String username;
    private String password;
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;
    private String nombreCliente;
    private String dni;
    private String email;

    private Set<String> roles;
}

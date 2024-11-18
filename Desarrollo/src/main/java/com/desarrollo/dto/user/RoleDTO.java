package com.desarrollo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RoleDTO {
    private long idRoles;
    private String name;
    private Set<String> permissions;
}

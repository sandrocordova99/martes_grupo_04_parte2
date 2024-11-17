package com.desarrollo.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data
@Builder
public class RoleDTO {
    private String name;
    private Set<String> permissions;
}

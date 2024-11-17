package com.desarrollo.service.user;

import com.desarrollo.dto.user.RoleDTO;
import com.desarrollo.model.PermisoEntity;
import com.desarrollo.model.RoleEnum;
import com.desarrollo.model.RolesEntity;
import com.desarrollo.repository.userRepository.PermisoRepository;
import com.desarrollo.repository.userRepository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermisoRepository permisoRepository;


    public RolesEntity createRoles(RoleDTO roleDTO){
        RolesEntity rolesEntity = RolesEntity.builder()
                .name(RoleEnum.valueOf(roleDTO.getName()))
                .permisoEntities(roleDTO.getPermissions().stream()
                        .map(nombrePermiso -> permisoRepository.findByName(nombrePermiso).orElseThrow(()-> new RuntimeException("Permiso no encontrado")))
                        .collect(Collectors.toSet()))
                .build();

        return roleRepository.save(rolesEntity);
    }

    public List<RoleDTO> listarRoles(){
        List<RolesEntity> lista = roleRepository.findAll();
        return lista.stream().map(roleEnty->{
            RoleDTO role = new RoleDTO();
            role.setIdRoles(roleEnty.getIdRoles());
            role.setName(roleEnty.getName().name());
            role.setPermissions(
                    roleEnty.getPermisoEntities()
                            .stream()
                            .map(PermisoEntity::getName)
                            .collect(Collectors.toSet())
            );
            return role;
        }).collect(Collectors.toList());
    }
}

package com.desarrollo.service.user;

import com.desarrollo.dto.user.UserDTO;
import com.desarrollo.model.RoleEnum;
import com.desarrollo.model.UserEntity;
import com.desarrollo.repository.userRepository.RoleRepository;
import com.desarrollo.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsSeriveImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.buscarUsuarioPorNombre(username);
                //.orElseThrow(()->new UsernameNotFoundException("El usuario no existe"));

        List<GrantedAuthority> authorityList = new ArrayList<>();

        //obtener roles
        userEntity.getRolesEntitySet().forEach(role-> authorityList
                .add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))));

        //obtener permisos
        userEntity.getRolesEntitySet().stream()
                .flatMap(role->role.getPermisoEntities().stream())
                .forEach(permisos->authorityList.add(new SimpleGrantedAuthority(permisos.getName())));

        return new User(userEntity.getUsername()
        ,userEntity.getPassword()
        ,userEntity.isAccountNoExpired()
        ,userEntity.isEnabled()
        ,userEntity.isCredentialNoExpired()
        ,userEntity.isAccountNoLocked(),authorityList);
    }

    //crear usuario
    public UserEntity crearUser(UserDTO userDTO){
        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .isEnabled(userDTO.isEnabled())
                .rolesEntitySet(
                        userDTO.getRoles().stream()
                        .map(nombreRoles->roleRepository.findByName(RoleEnum.valueOf(nombreRoles))
                                .orElseThrow(()->new RuntimeException("Role no existe")))
                                .collect(Collectors.toSet())
                )
                .build();

     return userRepository.save(userEntity);
    }

}

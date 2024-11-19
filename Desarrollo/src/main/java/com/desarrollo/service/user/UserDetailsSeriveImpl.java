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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsSeriveImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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

    // Método para verificar si un DNI ya está registrado
    public boolean isDniRegistered(String dni) {
        return userRepository.existsByDni(dni);
    }

    // Método para verificar si un email ya está registrado
    public boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    //crear usuario
    public UserEntity crearUser(UserDTO userDTO){
        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                .nombreCliente(userDTO.getNombreCliente())
                .isEnabled(userDTO.isEnabled())
                .accountNoExpired(userDTO.isAccountNoExpired())
                .accountNoLocked(userDTO.isAccountNoLocked())
                .credentialNoExpired(userDTO.isCredentialNoExpired())
                .dni(userDTO.getDni())
                .email(userDTO.getEmail())
                .rolesEntitySet(
                        userDTO.getRoles().stream()
                        .map(nombreRoles->roleRepository.findByName(RoleEnum.valueOf(nombreRoles))
                                .orElseThrow(()->new RuntimeException("Role no existe")))
                                .collect(Collectors.toSet())
                )
                .build();

     return userRepository.save(userEntity);
    }

    public boolean authenticate(String username, String password) {
        UserEntity userEntity = userRepository.buscarUsuarioPorNombre(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return passwordEncoder.matches(password, userEntity.getPassword());
    }

}

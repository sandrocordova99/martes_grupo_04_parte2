package com.desarrollo.controller;

import com.desarrollo.dto.user.UserDTO;
import com.desarrollo.service.user.UserDetailsSeriveImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsSeriveImpl authService;
    @PostMapping("/registar")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            authService.crearUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    /**
     * endpoint para iniciar sesión
     */
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        try {
            boolean auth = authService.authenticate(userDTO.getUsername(), userDTO.getPassword());
            if(auth){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bienvenido" + userDTO.getUsername());
            }else{
                return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error en usuario o contraseña" + userDTO.getUsername());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en la autenticación: " + e.getMessage());
        }

    }

}

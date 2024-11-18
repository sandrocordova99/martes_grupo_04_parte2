package com.desarrollo.controller;

import com.desarrollo.dto.user.UserDTO;
import com.desarrollo.service.user.UserDetailsSeriveImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserDetailsSeriveImpl authService;
    @PostMapping("/registar")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            // Validación para el DNI
            if (authService.isDniRegistered((userDTO.getDni()))) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El DNI ya está registrado");
            }
            // Validación para el email
            if (authService.isEmailRegistered(userDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El email ya está registrado");
            }
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
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO userDTO) {
        try {
            boolean auth = authService.authenticate(userDTO.getUsername(), userDTO.getPassword());
            Map<String, String> response = new HashMap<>();
            if(auth){
                response.put("message","Bienvenido" + userDTO.getUsername());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }else{
                response.put("message","Error en usuario o contraseña" );
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error en la autenticación: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }

}

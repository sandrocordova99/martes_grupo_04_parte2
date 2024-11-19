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
    public ResponseEntity<Map<String, ?>> register(@RequestBody UserDTO userDTO) {
        System.out.println("DTO recibido: " + userDTO);
        Map<String, String> response = new HashMap<>();
        try {
            // Validación para el DNI
            if (authService.isDniRegistered((userDTO.getDni()))) {
                response.put("message","Error:El DNI ya está registrado");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(response);
            }
            // Validación para el email
            if (authService.isEmailRegistered(userDTO.getEmail())) {
                response.put("message","El email ya está registrado");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(response);
            }
            authService.crearUser(userDTO);
            response.put("message","Usuario registrado exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("message","Error al registrar el usuario");
            response.put("value" , e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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

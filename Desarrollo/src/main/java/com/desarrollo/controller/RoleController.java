package com.desarrollo.controller;

import com.desarrollo.dto.user.RoleDTO;
import com.desarrollo.model.RolesEntity;
import com.desarrollo.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")

@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolesEntity>crearRoles(@RequestBody RoleDTO roleDTO){
        return new ResponseEntity<>(roleService.createRoles(roleDTO), HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?>listar(){
        return new ResponseEntity<>(roleService.listarRoles(),HttpStatus.OK);
    }

    @GetMapping("/saludo")
    @PreAuthorize("hasRole('ADMIN')")
    public String saludo(){
        return "Hola mundo";
    }

    @GetMapping("/saludo2")
    @PreAuthorize("hasRole('INVITED')")
    public String saludo2(){
        return "Hola mundo";
    }

    @GetMapping("/disponible")
    @PreAuthorize("permitAll()")
    public String saludo3(){
        return "ruta no pregida";
    }
}

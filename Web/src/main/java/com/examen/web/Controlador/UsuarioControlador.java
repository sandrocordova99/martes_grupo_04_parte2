package com.examen.web.Controlador;

import com.examen.web.Entidades.Usuario;
import com.examen.web.Servicio.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioControlador(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> RegistrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/index")
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("Usuario Index hola mundo", HttpStatus.OK);
    }

    @GetMapping("/index2")
    public ResponseEntity<String> index2(){
        return new ResponseEntity<>("Usuario Index hola mundo sin autentificacion", HttpStatus.OK);
    }
}





package com.universidad.flota.controller;

import com.universidad.flota.domain.Usuario;
import com.universidad.flota.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        return usuarioService.login(email, password)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.status(401).body("Credenciales Invalidas"));
    }
}
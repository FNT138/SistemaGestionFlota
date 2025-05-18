package com.universidad.flota.service;

import com.universidad.flota.domain.Usuario;
import java.util.Optional;

public interface UsuarioService {
    Usuario findByEmail(String email);
    Optional<Usuario> login(String email, String rawPassword);
}
package com.universidad.flota.service;

import com.universidad.flota.domain.Usuario;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String email, String rawPassword);
}
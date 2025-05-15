package com.universidad.flota.service;

import com.universidad.flota.domain.Usuario;
import com.universidad.flota.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired private UsuarioRepository repo;
    @Autowired private PasswordEncoder encoder;

    @Override
    public Optional<Usuario> login(String email, String rawPassword) {
        return repo.findByEmail(email)
                .filter(u -> encoder.matches(rawPassword, u.getPassword()));
    }
}

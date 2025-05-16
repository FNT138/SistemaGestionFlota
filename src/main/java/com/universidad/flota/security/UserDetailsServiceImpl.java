package com.universidad.flota.security;

import com.universidad.flota.domain.Usuario;
import com.universidad.flota.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        System.out.println("Cargando usuario: " + email + ", rol=" + u.getRol().name());

        return User.builder()
                .username(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRol().name())
                .build();
    }
}

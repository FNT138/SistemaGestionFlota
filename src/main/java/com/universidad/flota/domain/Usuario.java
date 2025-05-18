package com.universidad.flota.domain;

import javax.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name = "usuarios")
@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    //public String getEmail() {
      //  return email;
    //}

    @Column(nullable = false)
    private String password;

    //public String getPassword() {
        //return password;
    //}

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    //public Rol getRol() {        return rol;    }

    //Metodo de conveniencia para login
    public boolean login(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.password);
    }
}
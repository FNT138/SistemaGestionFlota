package com.universidad.flota.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // usuario en memoria para desarrollo
        auth.inMemoryAuthentication()
                .withUser("solicitante").password(passwordEncoder().encode("pass")).roles("SOLICITANTE")
                .and()
                .withUser("encargado").password(passwordEncoder().encode("pass")).roles("ENCARGADO");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //para simplificar en dev
            .authorizeRequests()
                .antMatchers("/solicitante/**").hasRole("SOLICITANTE")
                .antMatchers("/encargado/**").hasRole("ENCARGADO")
                .anyRequest().authenticated()
            .and()
            .httpBasic(); //autenticacion basica HTTP
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
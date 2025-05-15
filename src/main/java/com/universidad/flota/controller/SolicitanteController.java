package com.universidad.flota.controller;

import com.universidad.flota.domain.*;
import com.universidad.flota.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {
    @Autowired private SolicitudService solicitudService;

    @PostMapping("/solicitudes")
    public SolicitudViaje crear(@RequestBody SolicitudViaje dto,
                                @AuthenticationPrincipal Usuario user){
        dto.setUsuario(user);
        return solicitudService.crearSolicitud(dto);
    }

    @GetMapping("/solicitudes")
    public List<SolicitudViaje> listar(@AuthenticationPrincipal Usuario user){
        return solicitudService.listarPorUsuario(user);
    }
}
package com.universidad.flota.controller;

import com.universidad.flota.domain.*;
import com.universidad.flota.dto.SolicitudViajeRequest;
import com.universidad.flota.dto.SolicitudViajeResponse;
import com.universidad.flota.service.SolicitudService;
import com.universidad.flota.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {
    @Autowired private SolicitudService solicitudService;

    @Autowired private UsuarioService usuarioService;

    @PostMapping("/solicitudes")
    public SolicitudViaje crear(@RequestBody SolicitudViajeRequest req,
                                @AuthenticationPrincipal UserDetails userDetails) {

        // Obtener la entidad Usuario
        Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());

        // Mapear DTO a entidad
        SolicitudViaje sol = SolicitudViaje.builder()
                .fechaSalida(req.getFechaSalida())
                .fechaRegreso(req.getFechaRegreso())
                .destino(req.getDestino())
                .motivo(req.getMotivo())
                .prioridad(req.getPrioridad())
                .estado(EstadoSolicitud.PENDIENTE)
                .usuario(usuario)
                .build();

        return solicitudService.crearSolicitud(sol);
    }

    @GetMapping("/solicitudes")
    public List<SolicitudViajeResponse> listar(@AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
        List<SolicitudViaje> lista = solicitudService.listarPorUsuario(usuario);

        return lista.stream()
                .map(sol -> SolicitudViajeResponse.builder()
                        .id(sol.getId())
                        .fechaSalida(sol.getFechaSalida())
                        .fechaRegreso(sol.getFechaRegreso())
                        .destino(sol.getDestino())
                        .motivo(sol.getMotivo())
                        .prioridad(sol.getPrioridad())
                        .estado(sol.getEstado())
                        .usuarioId(sol.getUsuario().getId())
                        .usuarioEmail(sol.getUsuario().getEmail())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
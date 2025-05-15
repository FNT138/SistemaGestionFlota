package com.universidad.flota.controller;

import com.universidad.flota.domain.EstadoSolicitud;
import com.universidad.flota.domain.SolicitudViaje;
import com.universidad.flota.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/encargado")
public class EncargadoController {
    @Autowired private SolicitudService solicitudService;

    @GetMapping("/pendientes")
    public List<SolicitudViaje> pendienes() {
        return solicitudService.listarPorEstado(EstadoSolicitud.PENDIENTE);
    }

    @PutMapping("/solicitudes/{id}/estado")
    public SolicitudViaje actualizarEstado(@PathVariable Long id,
                                           @RequestParam EstadoSolicitud estado) {
        return solicitudService.actualizarEstado(id, estado);
    }
}

package com.universidad.flota.controller;

import com.universidad.flota.domain.SolicitudViaje;
import com.universidad.flota.service.AprobacionService;
import com.universidad.flota.repository.SolicitudViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.Optional;

@RestController
@RequestMapping("/encargado")
public class EncargadoController {

    @Autowired
    private SolicitudViajeRepository solicitudViajeRepo;

    @Autowired
    private AprobacionService aprobacionService;

    @PostMapping("/solicitudes/{id}/aprobar")
    public void aprobarSolicitud(@PathVariable Long id,
                                 @RequestParam(required = false) String comentarios) {

        Optional<SolicitudViaje> solicitudopt = solicitudViajeRepo.findById(id);
        if (solicitudopt.isPresent()) {
            aprobacionService.aprobar(solicitudopt.get(), comentarios);
        } else {
            throw new RuntimeException("Solicitud no encontrada");
        }
    }

    @PostMapping("/solicitudes/{id}/rechazar")
    public void rechazarSolicitud(@PathVariable Long id,
                                  @RequestParam(required = false) String comentarios) {
        Optional<SolicitudViaje> solicitudopt = solicitudViajeRepo.findById(id);
        if (solicitudopt.isPresent()) {
            aprobacionService.rechazar(solicitudopt.get(), comentarios);
        } else {
            throw new RuntimeException("Solicitud no encontrada");
        }
    }
}

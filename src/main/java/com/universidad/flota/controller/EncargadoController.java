package com.universidad.flota.controller;

import com.universidad.flota.domain.SolicitudViaje;
import com.universidad.flota.domain.Vehiculo;
import com.universidad.flota.domain.Viaje;
import com.universidad.flota.service.AprobacionService;
import com.universidad.flota.repository.SolicitudViajeRepository;
import com.universidad.flota.service.AsignacionService;
import com.universidad.flota.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private ViajeService viajeService;

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

    @PostMapping("/solicitudes/{id}/asignar/{vid}")
    public Vehiculo asignarVehiculo(@PathVariable Long sid,
                                    @PathVariable Long vid) {
        return asignacionService.asignarVehiculo(sid, vid);
    }

    @PostMapping("/solicitudes/{sid}/viaje")
    public Viaje registrarViaje(
            @PathVariable Long sid,
            @RequestParam Double kmInicio,
            @RequestParam Double kmFin,
            @RequestParam Double combustibleInicio,
            @RequestParam Double combustibleFin){

        return viajeService.registrarViaje(sid, kmInicio,kmFin, combustibleInicio, combustibleFin);

    }
}

package com.universidad.flota.controller;

import com.universidad.flota.domain.Aprobacion;
import com.universidad.flota.service.AprobacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encargado")
public class EncargadoController {

    @Autowired
    private AprobacionService aprobacionService;

    @PostMapping("/aprobar/{solicitudId}")
    public ResponseEntity<Aprobacion> aprobarSolicitud(@PathVariable Long solicitudId,
                                                       @RequestParam(required = false) String comentarios) {
        Aprobacion aprobacion = aprobacionService.aprobarSolicitud(solicitudId);
        return ResponseEntity.ok(aprobacion);
    }

    @PostMapping("/rechazar/{solicitudId}")
    public ResponseEntity<Aprobacion> rechazarSolicitud(@PathVariable Long solicitudId,
                                                        @RequestParam(required = false) String comentarios) {
        Aprobacion aprobacion = aprobacionService.rechazarSolicitud(solicitudId);
        return ResponseEntity.ok(aprobacion);
    }
}

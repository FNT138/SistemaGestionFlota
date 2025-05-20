package com.universidad.flota.controller;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.*;
import com.universidad.flota.service.*;
import com.universidad.flota.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/encargado")
public class EncargadoController {

    @Autowired
    private AprobacionService aprobacionService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private ViajeService viajeService;

    @Autowired
    private IncidenteService incidenteService;

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping("/solicitudes/pendientes")
    public List<SolicitudViajeResponse> verSolicitudesPendientes(){
        List<SolicitudViaje> pendientes = solicitudService.listarPorEstado(EstadoSolicitud.PENDIENTE);

        return pendientes.stream()
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

    @PostMapping("/solicitudes/{id}/aprobar")
    public AprobacionResponse aprobarSolicitud(@PathVariable Long id,
                                 @RequestBody ComentarioRequest req) {

        Aprobacion aprobacion = aprobacionService.aprobar(solicitudService.findById(id), req.getComentarios());
        return AprobacionResponse.fromEntity(aprobacion);
    }

    @PostMapping("/solicitudes/{id}/rechazar")
    public AprobacionResponse rechazarSolicitud(@PathVariable Long id,@RequestBody ComentarioRequest req) {

       Aprobacion aprobacion = aprobacionService.rechazar(solicitudService.findById(id), req.getComentarios());
        return AprobacionResponse.fromEntity(aprobacion);
    }

    @PostMapping("/solicitudes/{sid}/asignar/{vid}")
    public Vehiculo asignarVehiculo(@PathVariable Long sid,
                                    @PathVariable Long vid) {
        return asignacionService.asignarVehiculo(sid, vid);
    }

    @PostMapping("/solicitudes/{sid}/viaje")
    public ViajeDTO registrarViaje(
            @PathVariable("sid") Long sid,
            @RequestParam Double kmInicio,
            @RequestParam Double kmFin,
            @RequestParam Double combustibleInicio,
            @RequestParam Double combustibleFin){

        return viajeService.registrarViaje(sid, kmInicio, kmFin, combustibleInicio, combustibleFin);
    }

}

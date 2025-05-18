package com.universidad.flota.controller;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.*;
import com.universidad.flota.service.*;
import com.universidad.flota.dto.*;
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

    @Autowired
    private MantenimientoService mantenimientoService;

    @Autowired
    private IncidenteService incidenteService;

    @Autowired
    private VehiculoRepository vehiculoRepo;


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

    @PostMapping("/vehiculos/{vid}/mantenimiento")
    public Mantenimiento registrarMantenimiento(@PathVariable("vid") Long vid,
                                                @RequestBody MantenimientoRequest dto){

        if (!vid.equals(dto.getVehiculoId())){
            throw new IllegalArgumentException("El ID del vehiculo en la ruta y el body no coinciden");
        }

        //crear la entidad a partir del DTO
        Mantenimiento mant = Mantenimiento.builder()
                .vehiculo(vehiculoRepo.findById(dto.getVehiculoId())
                        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado")))
                .fecha(dto.getFecha())
                .tipoSerivicio(dto.getTipoServicio())
                .km(dto.getKm())
                .facturaAdjunta(dto.getFacturaAdjunta())
                .build();

        return mantenimientoService.registrar(mant);
    }

    @PostMapping("/vehiculos/{vid}/incidente")
    public Incidente registrarIncidente(@PathVariable("vid") Long vid,
                                                @RequestBody IncidenteRequest dto){

        if (!vid.equals(dto.getVehiculoId())) throw new IllegalArgumentException();


        //crear la entidad a partir del DTO
        Incidente inc = Incidente.builder()
                .vehiculo(vehiculoRepo.findById(vid).get())
                .fecha(dto.getFecha())
                .descripcion(dto.getDescripcion())
                .partePolicial(dto.getPartePolicial())
                .fotos(dto.getFotos())
                .build();

        return incidenteService.registrar(inc);
    }

}

package com.universidad.flota.controller;

import com.universidad.flota.domain.Mantenimiento;
import com.universidad.flota.dto.*;
import com.universidad.flota.mapper.MantenimientoMapper;
import com.universidad.flota.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculos")
public class MantenimientoController {

    @Autowired
    private  MantenimientoService mantenimientoService;

    @Autowired
    private MantenimientoMapper mantenimientoMapper;

    @Autowired
    public MantenimientoController(MantenimientoService mantenimientoService,
                                   MantenimientoMapper mantenimientoMapper) {
        this.mantenimientoService = mantenimientoService;
        this.mantenimientoMapper = mantenimientoMapper;
    }

    // 1) Reservar turno
    @PostMapping("/{vehiculoId}/mantenimiento/reservar")
    public MantenimientoResponse reservar(
            @PathVariable Long vehiculoId,
            @RequestBody MantenimientoReservationDTO dto) {

        Mantenimiento mant = mantenimientoService.reservar(
                vehiculoId,
                dto.getFechaProgramada(),
                dto.getTipoServicio());

        return mantenimientoMapper.toResponseDTO(mant);
    }

    // 2) Iniciar mantenimiento programado
    @PostMapping("/mantenimiento/{mid}/iniciar")
    public MantenimientoResponse iniciar(@PathVariable Long mid) {
        return mantenimientoMapper.toResponseDTO(mantenimientoService.iniciar(mid));
    }

    // 3) Cerrar mantenimiento
    @PostMapping("/mantenimiento/{mid}/cerrar")
    public MantenimientoResponse cerrar(
            @PathVariable("mid") Long mid,
            @RequestBody MantenimientoCloseDTO dto) {
        Mantenimiento mant = mantenimientoService.cerrar(mid, dto.getFacturaAdjunta());
        return mantenimientoMapper.toResponseDTO(mant);
    }


    // 4) Marcar manualmente fuera de servicio
    @PostMapping("/{vehiculoId}/fuera-servicio")
    public VehiculoResponse fueraDeServicio(@PathVariable Long vehiculoId) {
        return mantenimientoMapper.toVehiculoResponse(mantenimientoService.marcarFueraDeServicio(vehiculoId));
    }


}

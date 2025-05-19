package com.universidad.flota.controller;

import com.universidad.flota.domain.Mantenimiento;
import com.universidad.flota.domain.Vehiculo;
import com.universidad.flota.dto.MantenimientoCloseDTO;
import com.universidad.flota.dto.ReservationDTO;
import com.universidad.flota.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encargado")
public class MantenimientoController {

    @Autowired
    private  MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    // 1) Reservar turno
    @PostMapping("/vehiculos/{vehiculoId}/mantenimiento/reservar")
    public Mantenimiento reservar(
            @PathVariable Long vehiculoId,
            @RequestBody ReservationDTO dto) {
        return mantenimientoService.reservar(vehiculoId, dto.getFechaProgramada(), dto.getTipoServicio());
    }

    // 2) Iniciar mantenimiento programado
    @PostMapping("/mantenimiento/{id}/iniciar")
    public Mantenimiento iniciar(@PathVariable Long id) {
        return mantenimientoService.iniciar(id);
    }

    // 3) Cerrar mantenimiento
    @PostMapping("/mantenimiento/{id}/cerrar")
    public Mantenimiento cerrar(
            @PathVariable Long id,
            @RequestBody MantenimientoCloseDTO dto) {
        return mantenimientoService.cerrar(id, dto.getFacturaAdjunta());
    }

    // 4) Marcar manualmente fuera de servicio
    @PostMapping("/vehiculos/{vehiculoId}/fuera-servicio")
    public Vehiculo fueraDeServicio(@PathVariable Long vehiculoId) {
        return mantenimientoService.marcarFueraDeServicio(vehiculoId);
    }


}

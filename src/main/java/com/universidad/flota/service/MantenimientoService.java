package com.universidad.flota.service;

import com.universidad.flota.domain.Mantenimiento;
import com.universidad.flota.domain.Vehiculo;

import java.time.LocalDateTime;

public interface MantenimientoService {

    Mantenimiento reservar(Long vehiculoId,
                           LocalDateTime fechaProgramada,
                           String tipoServicio);

    Mantenimiento iniciar (Long id);

    Mantenimiento cerrar(Long id, String factura);

    Vehiculo marcarFueraDeServicio(Long vehiculoId);

}

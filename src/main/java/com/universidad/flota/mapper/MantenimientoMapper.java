// src/main/java/com/universidad/flota/mapper/MantenimientoMapper.java
package com.universidad.flota.mapper;

import com.universidad.flota.domain.Mantenimiento;
import com.universidad.flota.dto.MantenimientoResponse;
import com.universidad.flota.dto.VehiculoResponse;
import org.springframework.stereotype.Component;

@Component
public class MantenimientoMapper {

    /** Mapea entidad Mantenimiento → DTO de respuesta */
    public MantenimientoResponse toResponseDTO(Mantenimiento mant) {
        if (mant == null) return null;
        return new MantenimientoResponse(
                mant.getId(),
                mant.getVehiculo().getId(),
                mant.getFechaProgramada(),
                mant.getTipoServicio(),
                mant.getFechaInicioReal(),
                mant.getFechaFinReal(),
                mant.getFacturaAdjunta()
        );
    }

    /** Mapea entidad Vehiculo → DTO de respuesta ligero */
    public VehiculoResponse toVehiculoResponse(com.universidad.flota.domain.Vehiculo veh) {
        if (veh == null) return null;
        return VehiculoResponse.builder()
                .id(veh.getId())
                .patente(veh.getPatente())
                .modelo(veh.getModelo())
                .estado(veh.getEstado().name())
                .build();
    }
}

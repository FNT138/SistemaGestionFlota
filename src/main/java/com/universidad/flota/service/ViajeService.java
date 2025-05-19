package com.universidad.flota.service;

import com.universidad.flota.domain.Viaje;
import com.universidad.flota.dto.ViajeDTO;

public interface ViajeService {
    ViajeDTO registrarViaje(
            Long solicitudId,
            Double kmInicio,
            Double kmFin,
            Double combustibleInicio,
            Double combustibleFin
    );
}

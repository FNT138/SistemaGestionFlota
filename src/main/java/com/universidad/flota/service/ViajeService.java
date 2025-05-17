package com.universidad.flota.service;

import com.universidad.flota.domain.Viaje;

public interface ViajeService {
    Viaje registrarViaje(
            Long solicitudId,
            Double kmInicio,
            Double kmFin,
            Double combustibleInicio,
            Double combustibleFin
    );
}

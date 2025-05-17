package com.universidad.flota.service;

import com.universidad.flota.domain.SolicitudViaje;
import com.universidad.flota.domain.Vehiculo;

public interface AsignacionService {
    Vehiculo asignarVehiculo(Long solicitudId, Long vehiculoId);
}

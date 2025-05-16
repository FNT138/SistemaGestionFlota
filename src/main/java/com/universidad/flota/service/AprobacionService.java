package com.universidad.flota.service;

import com.universidad.flota.domain.Aprobacion;

public interface AprobacionService {
    Aprobacion aprobarSolicitud(Long solicitudId);
    Aprobacion rechazarSolicitud(Long solicitud);
}

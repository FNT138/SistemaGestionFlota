package com.universidad.flota.service;

import com.universidad.flota.domain.Aprobacion;
import com.universidad.flota.domain.SolicitudViaje;

public interface AprobacionService {
    void aprobar(SolicitudViaje solicitud,String comentario);
    void rechazar(SolicitudViaje solicitud,String comentario);
}

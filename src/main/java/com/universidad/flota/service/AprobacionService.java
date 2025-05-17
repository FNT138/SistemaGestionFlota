package com.universidad.flota.service;

import com.universidad.flota.domain.Aprobacion;
import com.universidad.flota.domain.SolicitudViaje;

public interface AprobacionService {
    Aprobacion aprobar(SolicitudViaje solicitud,String comentario);
    Aprobacion rechazar(SolicitudViaje solicitud,String comentario);
}

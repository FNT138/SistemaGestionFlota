package com.universidad.flota.service;

import com.universidad.flota.domain.SolicitudViaje;
import com.universidad.flota.domain.Usuario;
import com.universidad.flota.domain.EstadoSolicitud;
import java.util.List;

public interface SolicitudService {
    SolicitudViaje crearSolicitud(SolicitudViaje solicitud);
    List<SolicitudViaje> listarPorUsuario(Usuario usuario);
    List<SolicitudViaje> listarPorEstado(EstadoSolicitud estado);
    SolicitudViaje actualizarEstado(Long solicitudID, EstadoSolicitud nuevoEstado);
    SolicitudViaje findById(Long solicitudID);
}
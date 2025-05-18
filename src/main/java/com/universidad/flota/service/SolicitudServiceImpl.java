package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.SolicitudViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class SolicitudServiceImpl implements SolicitudService{
    @Autowired private SolicitudViajeRepository repo;

    @Override
    public SolicitudViaje crearSolicitud(SolicitudViaje solicitud){
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        return repo.save(solicitud);
    }

    @Override
    public List<SolicitudViaje> listarPorUsuario(Usuario usuario) {

        return repo.findByUsuarioId(usuario.getId());
    }

    @Override
    public List<SolicitudViaje> listarPorEstado(EstadoSolicitud estado){
        return repo.findByEstado(estado);
    }

    @Override
    public SolicitudViaje actualizarEstado(Long solicitudId, EstadoSolicitud nuevoEstado){
        SolicitudViaje sol = repo.findById(solicitudId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));
        sol.setEstado(nuevoEstado);
        return repo.save(sol);
    }
}
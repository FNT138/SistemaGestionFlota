package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.AprobacionRepository;
import com.universidad.flota.repository.SolicitudViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AprobacionServiceImpl implements AprobacionService{

    @Autowired
    private AprobacionRepository aprobacionRepo;

    @Autowired
    private SolicitudViajeRepository solicitudRepo;

    @Override
    public void aprobar (SolicitudViaje solicitud, String comentarios){
        solicitud.setEstado(EstadoSolicitud.APROBADO);
        solicitudRepo.save(solicitud);

        Aprobacion aprobacion = Aprobacion.builder()
                .solicitud(solicitud)
                .comentarios(comentarios)
                .estado(EstadoSolicitud.APROBADO)
                .fecha(LocalDateTime.now())
                .build();
        aprobacionRepo.save(aprobacion);
    }

    @Override
    public void rechazar(SolicitudViaje solicitud, String comentarios){
        solicitud.setEstado(EstadoSolicitud.RECHAZADO);
        solicitudRepo.save(solicitud);

        Aprobacion aprobacion = Aprobacion.builder()
                .solicitud(solicitud)
                .comentarios(comentarios)
                .estado(EstadoSolicitud.RECHAZADO)
                .fecha(LocalDateTime.now())
                .build();

        aprobacionRepo.save(aprobacion);
    }
}

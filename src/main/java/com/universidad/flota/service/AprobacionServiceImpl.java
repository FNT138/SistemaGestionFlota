package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.*;
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
    public Aprobacion aprobarSolicitud(Long solicitudId){
        SolicitudViaje solicitud = solicitudRepo.findById(solicitudId)
                .orElseThrow(()-> new RuntimeException("Solicitud no encontrada"));

        solicitud.setEstado(EstadoSolicitud.APROBADO);
        solicitudRepo.save(solicitud);

        Aprobacion aprobacion = Aprobacion.builder()
                .solicitud(solicitud)
                .estado(EstadoSolicitud.APROBADO)
                .fecha(LocalDateTime.now())
                .build();
        return aprobacionRepo.save(aprobacion);
    }

    @Override
    public Aprobacion rechazarSolicitud(Long solicitudId){
        SolicitudViaje solicitud = solicitudRepo.findById(solicitudId)
                .orElseThrow(()-> new RuntimeException("Solicitud no encontrada"));

        solicitud.setEstado(EstadoSolicitud.RECHAZADO);
        solicitudRepo.save(solicitud);

        Aprobacion aprobacion = Aprobacion.builder()
                .solicitud(solicitud)
                .estado(EstadoSolicitud.RECHAZADO)
                .fecha(LocalDateTime.now())
                .build();

        return aprobacionRepo.save(aprobacion);
    }
}

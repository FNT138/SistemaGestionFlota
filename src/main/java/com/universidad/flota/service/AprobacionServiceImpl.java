package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.AprobacionRepository;
import com.universidad.flota.repository.SolicitudViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class AprobacionServiceImpl implements AprobacionService{

    @Autowired
    private AprobacionRepository aprobacionRepo;

    @Autowired
    private SolicitudViajeRepository solicitudRepo;

    @Autowired
    private SolicitudService solicitudService;

    @Override
    @Transactional
    public Aprobacion aprobar (SolicitudViaje solicitud, String comentarios) {

        // Validar estado actual de la solicitud
        if(solicitud.getEstado() != EstadoSolicitud.PENDIENTE){
            throw new IllegalArgumentException("La solicitud ya fue procesada y no puede volver a procesarse. Estado actual:"
                    + solicitud.getEstado());
        }

        // Actuallizo y guardo la solicitud
        solicitud.setEstado(EstadoSolicitud.APROBADO);
        solicitudRepo.save(solicitud);

        //Construyo la aptobacion con el comentario y estado correcto
        Aprobacion aprobacion = Aprobacion.builder()
                .solicitud(solicitud)
                .comentarios(comentarios)
                .estado(EstadoSolicitud.APROBADO)
                .fecha(LocalDateTime.now())
                .build();

        return aprobacionRepo.save(aprobacion);
    }

    @Override
    public Aprobacion rechazar(SolicitudViaje solicitud, String comentarios){

        // Validar estado actual de la solicitud
        if(solicitud.getEstado() != EstadoSolicitud.PENDIENTE){
            throw new IllegalArgumentException("La solicitud ya fue procesada y no puede volver a procesarse. Estado actual:"
                    + solicitud.getEstado());
        }

        solicitud.setEstado(EstadoSolicitud.RECHAZADO);
        solicitudRepo.save(solicitud);

        Aprobacion aprobacion = Aprobacion.builder()
                .solicitud(solicitud)
                .comentarios(comentarios)
                .estado(EstadoSolicitud.RECHAZADO)
                .fecha(LocalDateTime.now())
                .build();

       return aprobacionRepo.save(aprobacion);
    }
}

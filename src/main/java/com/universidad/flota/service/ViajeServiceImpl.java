package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ViajeServiceImpl implements ViajeService{

    @Autowired
    private ViajeRepository viajeRepo;

    @Autowired
    private SolicitudViajeRepository solicitudRepo;

    @Override
    public Viaje registrarViaje(Long solicitudId,
                                Double kmInicio, Double kmFin,
                                Double combustibleInicio, Double combustibleFin) {
        SolicitudViaje solicitud = solicitudRepo.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitud.getEstado() != EstadoSolicitud.ASIGNADA) {
            throw new RuntimeException("La solicitud debe ser ASIGNADA para registrar el viaje");
        }

        Viaje viaje = Viaje.builder()
                .solicitud(solicitud)
                .kmInicio(kmInicio)
                .kmFin(kmFin)
                .combustibleInicio(combustibleInicio)
                .combustibleFin(combustibleFin)
                .fechaInicio(LocalDateTime.now())
                .build();

        //opcional, cerrar solicitudes
        solicitud.setEstado(EstadoSolicitud.CERRADA);
        solicitudRepo.save(solicitud);

        return viajeRepo.save(viaje);

    }
}

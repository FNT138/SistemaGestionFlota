package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.dto.*;
import com.universidad.flota.mapper.*;
import com.universidad.flota.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class ViajeServiceImpl implements ViajeService{

    @Autowired
    private ViajeRepository viajeRepo;

    @Autowired
    private SolicitudViajeRepository solicitudRepo;

    @Override
    @Transactional
    public ViajeDTO registrarViaje(Long solicitudId,
                                   Double kmInicio, Double kmFin,
                                   Double combustibleInicio, Double combustibleFin) {

        SolicitudViaje sol = solicitudRepo.findById(solicitudId)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada"));


        Viaje v = new Viaje();
        v.setSolicitud(sol);
        v.setKmInicio(kmInicio);
        v.setKmFin(kmFin);
        v.setCombustibleInicio(combustibleInicio);
        v.setCombustibleFin(combustibleFin);
        viajeRepo.save(v);

        sol.setEstado(EstadoSolicitud.CERRADA);
        solicitudRepo.save(sol);



        return new ViajeDTO(
                v.getId(),
                solicitudId,
                kmInicio,
                kmFin,
                combustibleInicio,
                combustibleFin
        );
    }

}

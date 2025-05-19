package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignacionServiceImpl implements AsignacionService {

    @Autowired
    private SolicitudViajeRepository solicitudRepo;

    @Autowired
    private VehiculoRepository vehiculoRepo;
    @Autowired
    private SolicitudViajeRepository solicitudViajeRepository;

    @Override
    public Vehiculo asignarVehiculo(Long solicitudId, Long vehiculoId) {

        SolicitudViaje solicitud = solicitudRepo.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        if (solicitud.getEstado() != EstadoSolicitud.APROBADO){
            throw new RuntimeException("Solo se pueden asignar vehiculos a solicitudes aprobadas");
        }

        Vehiculo vehiculo= vehiculoRepo.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrada"));

        if (vehiculo.getEstado() != EstadoVehiculo.DISPONIBLE){
            throw new RuntimeException("Vehiculo no disponible");
        }

        //asignar
        solicitud.setVehiculo(vehiculo);
        solicitud.setEstado(EstadoSolicitud.ASIGNADA);
        solicitudRepo.save(solicitud);


        vehiculo.setEstado(EstadoVehiculo.ASIGNADO);
        return vehiculoRepo.save(vehiculo);


    }


}

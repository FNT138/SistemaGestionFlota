package com.universidad.flota.repository;

import com.universidad.flota.domain.SolicitudViaje;
import com.universidad.flota.domain.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitudViajeRepository extends JpaRepository<SolicitudViaje, Long>{
    List<SolicitudViaje> findByEstado(EstadoSolicitud estado);
    List<SolicitudViaje> findByUsuarioId(Long usuarioId);
}
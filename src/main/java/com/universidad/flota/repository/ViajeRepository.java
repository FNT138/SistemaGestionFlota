package com.universidad.flota.repository;

import com.universidad.flota.domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    Viaje findBySolicitudId(Long solicitudId);
}

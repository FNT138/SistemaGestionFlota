package com.universidad.flota.repository;

import com.universidad.flota.domain.EstadoVehiculo;
import com.universidad.flota.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    //Solo lista los disponibles
    List<Vehiculo> findByEstado(EstadoVehiculo estado);
}

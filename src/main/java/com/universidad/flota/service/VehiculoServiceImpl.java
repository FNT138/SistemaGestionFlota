package com.universidad.flota.service;

import com.universidad.flota.domain.*;
import com.universidad.flota.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Override
    public List<Vehiculo> listarDisponibles() {
        return vehiculoRepo.findByEstado(EstadoVehiculo.DISPONIBLE);
    }
}

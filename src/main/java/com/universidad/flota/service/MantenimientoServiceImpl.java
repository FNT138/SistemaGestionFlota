package com.universidad.flota.service;

import com.universidad.flota.domain.EstadoVehiculo;
import com.universidad.flota.domain.Mantenimiento;
import com.universidad.flota.domain.Vehiculo;
import com.universidad.flota.repository.MantenimientoRepository;
import com.universidad.flota.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class MantenimientoServiceImpl implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepo;

    @Autowired
    private VehiculoRepository vehiculoRepo;

    public MantenimientoServiceImpl(MantenimientoRepository mantenimientoRepo, VehiculoRepository vehiculoRepo) {
        this.mantenimientoRepo = mantenimientoRepo;
        this.vehiculoRepo = vehiculoRepo;
    }

    @Override
    @Transactional
    public Mantenimiento reservar(Long vehiculoId,
                                  LocalDateTime fechaProgramada,
                                  String tipoServicio){
        Vehiculo v = vehiculoRepo.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado"));

        var m = Mantenimiento.builder()
                .vehiculo(v)
                .fechaProgramada(fechaProgramada)
                .tipoSerivicio(tipoServicio)
                .build();
        return mantenimientoRepo.save(m);
    }

    @Override
    @Transactional
    public Mantenimiento iniciar(Long id) {
        Mantenimiento m = mantenimientoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        if (m.getFechaInicioReal()!= null){
            throw new IllegalStateException("Ya iniciado ");
        }

        m.setFechaInicioReal(LocalDateTime.now());

        Vehiculo v = m.getVehiculo();
        v.setEstado(EstadoVehiculo.MANTENIMIENTO);
        vehiculoRepo.save(v);
        return mantenimientoRepo.save(m);
    }

    @Override
    @Transactional
    public Mantenimiento cerrar(Long id, String factura){
        Mantenimiento m = mantenimientoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        if (m.getFechaInicioReal() == null){
            throw new IllegalStateException("No iniciado ");
        } else if (m.getFechaFinReal() != null) {
            throw new IllegalStateException("Ya cerrado ");
        }
        m.setFechaFinReal(LocalDateTime.now());
        m.setFacturaAdjunta(factura);

        Vehiculo v = m.getVehiculo();
        v.setEstado(EstadoVehiculo.DISPONIBLE);
        vehiculoRepo.save(v);
        return mantenimientoRepo.save(m);
    }

    @Override
    @Transactional
    public Vehiculo marcarFueraDeServicio(Long vid){
        Vehiculo v = vehiculoRepo.findById(vid)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado"));

        v.setEstado(EstadoVehiculo.FUERA_SERVICIO);
        return vehiculoRepo.save(v);

    }





}

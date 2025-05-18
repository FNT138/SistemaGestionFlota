package com.universidad.flota.service;

import com.universidad.flota.domain.Mantenimiento;
import com.universidad.flota.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MantenimientoServiceImpl implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepo;

    @Override
    public Mantenimiento registrar(Mantenimiento mant){
        return mantenimientoRepo.save(mant);
    }

}

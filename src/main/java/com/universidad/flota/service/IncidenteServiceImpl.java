package com.universidad.flota.service;

import com.universidad.flota.domain.Incidente;
import com.universidad.flota.repository.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenteServiceImpl implements IncidenteService{

    @Autowired
    private IncidenteRepository incidenteRepo;

    @Override
    public Incidente registrar(Incidente incidente) {
        return incidenteRepo.save(incidente);
    }

}

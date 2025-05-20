package com.universidad.flota.controller;

import com.universidad.flota.domain.*;
import com.universidad.flota.dto.IncidenteRequest;
import com.universidad.flota.repository.VehiculoRepository;
import com.universidad.flota.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Autowired
    private IncidenteService incidenteService;

    @GetMapping("/disponibles")
    public List<Vehiculo> listarDisponibles() {
        return vehiculoService.listarDisponibles();
    }
    @PostMapping("{vid}/incidente")
    public Incidente registrarIncidente(@PathVariable("vid") Long vid,
                                        @RequestBody IncidenteRequest dto){

        if (!vid.equals(dto.getVehiculoId())) throw new IllegalArgumentException();


        //crear la entidad a partir del DTO
        Incidente inc = Incidente.builder()
                .vehiculo(vehiculoRepo.findById(vid)
                        .orElseThrow(RuntimeException::new))
                .fecha(dto.getFecha())
                .descripcion(dto.getDescripcion())
                .partePolicial(dto.getPartePolicial())
                .fotos(dto.getFotos())
                .build();

        return incidenteService.registrar(inc);
    }

}

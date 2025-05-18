package com.universidad.flota.controller;

import com.universidad.flota.domain.*;
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

    @GetMapping("/disponibles")
    public List<Vehiculo> listarDisponibles() {
        return vehiculoService.listarDisponibles();
    }

}

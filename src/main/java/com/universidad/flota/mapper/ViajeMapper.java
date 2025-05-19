package com.universidad.flota.mapper;

import com.universidad.flota.domain.SolicitudViaje;
import com.universidad.flota.domain.Viaje;
import com.universidad.flota.dto.*;

import javax.xml.validation.Validator;

public class ViajeMapper {

    public static ViajeDTO toDTO(Viaje v) {

        return new ViajeDTO(
                v.getId(),
                v.getSolicitud().getId(),
                v.getKmInicio(),
                v.getKmFin(),
                v.getCombustibleInicio(),
                v.getCombustibleFin()
        );

    }
}

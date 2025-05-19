package com.universidad.flota.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

/**
 * DTO para cerrar un mantenimiento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MantenimientoCloseDTO {

    @NotBlank(message = "La factura adjunta es obligatoria")
    private String facturaAdjunta;
}

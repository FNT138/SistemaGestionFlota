package com.universidad.flota.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoReservationDTO {
    @NotNull
    private LocalDateTime fechaProgramada;

    @NotBlank
    private String tipoServicio;
}

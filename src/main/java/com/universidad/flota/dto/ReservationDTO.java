package com.universidad.flota.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO para reservar un mantenimiento de vehículo.
 * Solo expone los campos mínimos requeridos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
        private LocalDateTime fechaProgramada;
        private String tipoServicio;
    }
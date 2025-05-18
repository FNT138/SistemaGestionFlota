package com.universidad.flota.dto;

import com.universidad.flota.domain.EstadoVehiculo;
import lombok.*;
import java.time.LocalDateTime;


/**
 * DTO para recibir datos de mantenimiento
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MantenimientoRequest {
    private Long vehiculoId;
    private LocalDateTime fecha;
    private String tipoServicio;
    private Double km;
    private String facturaAdjunta;
}

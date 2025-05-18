package com.universidad.flota.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha;

    private String tipoServicio;
    private Double km;
    private String facturaAdjunta;
}

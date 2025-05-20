// src/main/java/com/universidad/flota/dto/MantenimientoResponse.java
package com.universidad.flota.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MantenimientoResponse {
    private Long id;
    private Long vehiculoId;
    private LocalDateTime fechaProgramada;
    private String tipoServicio;
    private LocalDateTime fechaInicioReal;
    private LocalDateTime fechaFinReal;
    private String facturaAdjunta;   // solo para cierre
}
